package ch.cyberduck.core.irods;

/*
 * Copyright (c) 2002-2015 David Kocher. All rights reserved.
 * http://cyberduck.ch/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * Bug fixes, suggestions and comments should be sent to feedback@cyberduck.ch
 */

import ch.cyberduck.core.Credentials;
import ch.cyberduck.core.DisabledCancelCallback;
import ch.cyberduck.core.DisabledHostKeyCallback;
import ch.cyberduck.core.DisabledLoginCallback;
import ch.cyberduck.core.Host;
import ch.cyberduck.core.Local;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.Profile;
import ch.cyberduck.core.ProtocolFactory;
import ch.cyberduck.core.features.Delete;
import ch.cyberduck.core.proxy.Proxy;
import ch.cyberduck.core.serializer.impl.dd.ProfilePlistReader;
import ch.cyberduck.core.transfer.TransferStatus;
import ch.cyberduck.test.IntegrationTest;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Category(IntegrationTest.class)
public class IRODSTouchFeatureTest {

    @Test
    public void testTouch() throws Exception {
        final ProtocolFactory factory = new ProtocolFactory(new HashSet<>(Collections.singleton(new IRODSProtocol())));
        final Profile profile = new ProfilePlistReader(factory).read(
                new Local("../profiles/iRODS (iPlant Collaborative).cyberduckprofile"));
        final Host host = new Host(profile, profile.getDefaultHostname(), new Credentials(
                System.getProperties().getProperty("irods.key"), System.getProperties().getProperty("irods.secret")
        ));

        final IRODSSession session = new IRODSSession(host);
        session.open(Proxy.DIRECT, new DisabledHostKeyCallback(), new DisabledLoginCallback(), new DisabledCancelCallback());
        session.login(Proxy.DIRECT, new DisabledLoginCallback(), new DisabledCancelCallback());
        final Path test = new Path(new IRODSHomeFinderService(session).find(), UUID.randomUUID().toString(), EnumSet.of(Path.Type.file));
        new IRODSTouchFeature(session).touch(test, new TransferStatus());
        assertTrue(new IRODSFindFeature(session).find(test));
        new IRODSDeleteFeature(session).delete(Collections.<Path>singletonList(test), new DisabledLoginCallback(), new Delete.DisabledCallback());
        assertFalse(new IRODSFindFeature(session).find(test));
        session.close();
    }
}
