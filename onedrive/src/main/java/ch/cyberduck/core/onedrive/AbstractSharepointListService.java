package ch.cyberduck.core.onedrive;

/*
 * Copyright (c) 2002-2020 iterate GmbH. All rights reserved.
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

import ch.cyberduck.core.AttributedList;
import ch.cyberduck.core.Cache;
import ch.cyberduck.core.ListProgressListener;
import ch.cyberduck.core.ListService;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.features.IdProvider;
import ch.cyberduck.core.onedrive.features.sharepoint.SiteDrivesListService;
import ch.cyberduck.core.onedrive.features.sharepoint.SitesListService;

import java.util.Optional;

import static ch.cyberduck.core.onedrive.SharepointListService.*;

public abstract class AbstractSharepointListService implements ListService {

    private final AbstractSharepointSession session;
    private final IdProvider idProvider;
    private Cache<Path> cache;

    public AbstractSharepointListService(final AbstractSharepointSession session, final IdProvider idProvider) {
        this.session = session;
        this.idProvider = idProvider;
    }

    public AbstractSharepointSession getSession() {
        return session;
    }

    @Override
    public final AttributedList<Path> list(final Path directory, final ListProgressListener listener) throws BackgroundException {
        if(directory.isRoot()) {
            return getRoot(directory, listener);
        }

        final AttributedList<Path> result = processList(directory, listener);
        if(result != AttributedList.<Path>emptyList()) {
            return result;
        }

        final GraphSession.ContainerItem container = session.getContainer(directory);
        if(container.getCollectionPath().map(p -> container.isContainerInCollection() && SITES_CONTAINER.equals(p.getName())).orElse(false)) {
            return addSiteItems(directory, listener);
        }

        final Optional<ListService> collectionListService = container.getCollectionPath().map(p -> {
            if(SITES_CONTAINER.equals(p.getName())) {
                return new SitesListService(session);
            }
            else if(DRIVES_CONTAINER.equals(p.getName())) {
                return new SiteDrivesListService(session);
            }
            return null;
        });
        if(collectionListService.isPresent() && (!container.isDefined() || container.isCollectionInContainer())) {
            return collectionListService.get().list(directory, listener);
        }

        return new GraphItemListService(session, idProvider).withCache(cache).list(directory, listener);
    }

    @Override
    public ListService withCache(final Cache<Path> cache) {
        this.cache = cache;
        idProvider.withCache(cache);
        return this;
    }

    AttributedList<Path> addSiteItems(final Path directory, final ListProgressListener listener) throws BackgroundException {
        final AttributedList<Path> list = new AttributedList<>();
        list.add(new Path(directory, DRIVES_NAME.getName(), DRIVES_NAME.getType(), DRIVES_NAME.attributes()));
        list.add(new Path(directory, SITES_NAME.getName(), SITES_NAME.getType(), SITES_NAME.attributes()));
        listener.chunk(directory, list);
        return list;
    }

    abstract AttributedList<Path> getRoot(final Path directory, final ListProgressListener listener) throws BackgroundException;

    AttributedList<Path> processList(final Path directory, final ListProgressListener listener) throws BackgroundException {
        return AttributedList.emptyList();
    }
}
