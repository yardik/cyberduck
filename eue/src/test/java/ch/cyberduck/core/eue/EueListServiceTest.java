package ch.cyberduck.core.eue;

/*
 * Copyright (c) 2002-2021 iterate GmbH. All rights reserved.
 * https://cyberduck.io/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

import ch.cyberduck.core.AlphanumericRandomStringService;
import ch.cyberduck.core.AttributedList;
import ch.cyberduck.core.DefaultPathPredicate;
import ch.cyberduck.core.DescriptiveUrl;
import ch.cyberduck.core.DisabledListProgressListener;
import ch.cyberduck.core.DisabledLoginCallback;
import ch.cyberduck.core.DisabledPasswordCallback;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.PathAttributes;
import ch.cyberduck.core.eue.io.swagger.client.model.ShareCreationResponseEntry;
import ch.cyberduck.core.exception.NotfoundException;
import ch.cyberduck.core.features.Delete;
import ch.cyberduck.core.transfer.TransferStatus;
import ch.cyberduck.test.IntegrationTest;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Collections;
import java.util.EnumSet;

import static ch.cyberduck.core.AbstractPath.Type.directory;
import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class EueListServiceTest extends AbstractEueSessionTest {

    @Test
    public void testListRoot() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final Path folder = new EueDirectoryFeature(session, fileid).mkdir(
                new Path(new AlphanumericRandomStringService().random(), EnumSet.of(directory)), new TransferStatus());
        final AttributedList<Path> list = new EueListService(session, fileid).list(folder.getParent(), new DisabledListProgressListener());
        assertTrue(list.contains(folder));
        assertNotNull(list.find(f -> f.attributes().getFileId().equals(EueResourceIdProvider.TRASH)));
        assertTrue(list.contains(new Path("Gelöschte Dateien", EnumSet.of(directory)).withAttributes(new PathAttributes().withFileId("TRASH"))));
        assertEquals(folder.attributes(), list.get(folder).attributes());
        new EueDeleteFeature(session, fileid).delete(Collections.singletonList(folder), new DisabledLoginCallback(), new Delete.DisabledCallback());
    }

    @Test
    public void testListTrash() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final Path folder = new Path("Gelöschte Dateien", EnumSet.of(directory));
        final AttributedList<Path> list = new EueListService(session, fileid).list(folder, new DisabledListProgressListener());
    }

    @Test
    public void testListForSharedFile() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final Path sourceFolder = new EueDirectoryFeature(session, fileid).mkdir(new Path(new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.directory)), new TransferStatus());
        final Path file = new Path(sourceFolder, new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.file));
        createFile(file, RandomUtils.nextBytes(0));
        assertTrue(new EueFindFeature(session, fileid).find(file));
        final ShareCreationResponseEntry shareCreationResponseEntry = createShare(fileid, file);
        final String shareName = shareCreationResponseEntry.getEntity().getName();
        final PathAttributes attr = new EueListService(session, fileid).list(sourceFolder, new DisabledListProgressListener()).get(file).attributes();
        assertNotNull(attr.getLink());
        assertEquals(attr.getLink(), new EueShareUrlProvider(session.getHost(), session.userShares()).toUrl(file).find(DescriptiveUrl.Type.signed));
        new EueDeleteFeature(session, fileid).delete(Collections.singletonList(sourceFolder), new DisabledPasswordCallback(), new Delete.DisabledCallback());
    }

    @Test
    public void testListForSharedFolder() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final Path sourceFolder = new EueDirectoryFeature(session, fileid).mkdir(new Path(new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.directory)), new TransferStatus());
        final Path folder2 = new EueDirectoryFeature(session, fileid).mkdir(new Path(sourceFolder, new AlphanumericRandomStringService().random(), EnumSet.of(directory)), new TransferStatus());
        assertTrue(new EueFindFeature(session, fileid).find(folder2));
        final ShareCreationResponseEntry shareCreationResponseEntry = createShare(fileid, folder2);
        final String shareName = shareCreationResponseEntry.getEntity().getName();
        final PathAttributes attr = new EueListService(session, fileid).list(sourceFolder, new DisabledListProgressListener()).get(folder2).attributes();
        assertNotNull(attr.getLink());
        assertEquals(attr.getLink(), new EueShareUrlProvider(session.getHost(), session.userShares()).toUrl(folder2).find(DescriptiveUrl.Type.signed));
        new EueDeleteFeature(session, fileid).delete(Collections.singletonList(sourceFolder), new DisabledPasswordCallback(), new Delete.DisabledCallback());
    }

    @Test
    public void testListContainingFolder() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final Path folder = new EueDirectoryFeature(session, fileid).mkdir(
                new Path(new AlphanumericRandomStringService().random(), EnumSet.of(directory)), new TransferStatus());
        assertTrue(new EueListService(session, fileid).list(folder, new DisabledListProgressListener()).isEmpty());
        final Path subfolder = new EueDirectoryFeature(session, fileid).mkdir(
                new Path(folder, new AlphanumericRandomStringService().random(), EnumSet.of(directory)), new TransferStatus());
        assertTrue(new EueListService(session, fileid).list(subfolder, new DisabledListProgressListener()).isEmpty());
        final AttributedList<Path> list = new EueListService(session, fileid).list(folder, new DisabledListProgressListener());
        assertFalse(list.isEmpty());
        assertTrue(list.contains(subfolder));
        assertEquals(subfolder.attributes(), list.get(subfolder).attributes());
        new EueDeleteFeature(session, fileid).delete(Collections.singletonList(folder), new DisabledLoginCallback(), new Delete.DisabledCallback());
        assertFalse((new EueFindFeature(session, fileid).find(folder, new DisabledListProgressListener())));
        assertFalse((new EueFindFeature(session, fileid).find(subfolder, new DisabledListProgressListener())));
    }

    @Test
    public void testListContainingFile() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        final Path folder = new EueDirectoryFeature(session, fileid).mkdir(
                new Path(new AlphanumericRandomStringService().random(), EnumSet.of(directory)), new TransferStatus());
        assertTrue(new EueListService(session, fileid).list(folder, new DisabledListProgressListener()).isEmpty());
        final Path file = new EueTouchFeature(session, fileid)
                .touch(new Path(folder, new AlphanumericRandomStringService().random(), EnumSet.of(Path.Type.file)), new TransferStatus().withLength(0L));
        final AttributedList<Path> list = new EueListService(session, fileid).list(folder, new DisabledListProgressListener());
        assertFalse(list.isEmpty());
        assertNotNull(list.find(new DefaultPathPredicate(file)));
        assertEquals(file.attributes().getFileId(), list.get(file).attributes().getFileId());
        new EueDeleteFeature(session, fileid).delete(Collections.singletonList(folder), new DisabledLoginCallback(), new Delete.DisabledCallback());
        assertFalse((new EueFindFeature(session, fileid).find(folder, new DisabledListProgressListener())));
        assertFalse((new EueFindFeature(session, fileid).find(file, new DisabledListProgressListener())));
    }

    @Test(expected = NotfoundException.class)
    public void testNotFound() throws Exception {
        final EueResourceIdProvider fileid = new EueResourceIdProvider(session);
        new EueListService(session, fileid).list(new Path(new AlphanumericRandomStringService().random(), EnumSet.of(directory)), new DisabledListProgressListener());
    }
}
