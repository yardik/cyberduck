package ch.cyberduck.core.http;

/*
 * Copyright (c) 2002-2013 David Kocher. All rights reserved.
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

import ch.cyberduck.core.ConnectionCallback;
import ch.cyberduck.core.MimeTypeService;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.exception.BackgroundException;
import ch.cyberduck.core.features.AttributesAdapter;
import ch.cyberduck.core.shared.AppendWriteFeature;
import ch.cyberduck.core.threading.NamedThreadFactory;
import ch.cyberduck.core.transfer.TransferStatus;
import ch.cyberduck.core.worker.DefaultExceptionMappingService;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

import com.google.common.util.concurrent.Uninterruptibles;

public abstract class AbstractHttpWriteFeature<R> extends AppendWriteFeature<R> implements HttpWriteFeature<R> {
    private static final Logger log = LogManager.getLogger(AbstractHttpWriteFeature.class);

    private final AttributesAdapter<R> attributes;

    public AbstractHttpWriteFeature(final AttributesAdapter<R> attributes) {
        this.attributes = attributes;
    }

    private abstract class FutureHttpResponse implements Runnable {
        Exception exception;
        R response;

        public Exception getException() {
            return exception;
        }

        public R getResponse() {
            return response;
        }
    }

    /**
     * @param command Callable writing entity to stream and returning checksum
     * @return Outputstream to write entity into.
     */
    @Override
    public HttpResponseOutputStream<R> write(final Path file, final TransferStatus status,
                                             final DelayedHttpEntityCallable<R> command) throws BackgroundException {
        return this.write(file, status, command, new DelayedHttpEntity() {
            @Override
            public long getContentLength() {
                return command.getContentLength();
            }
        });
    }

    public HttpResponseOutputStream<R> write(final Path file, final TransferStatus status,
                                             final DelayedHttpEntityCallable<R> command, final DelayedHttpEntity entity) throws BackgroundException {
        // Signal on enter streaming
        final CountDownLatch entry = entity.getEntry();
        final CountDownLatch exit = new CountDownLatch(1);
        if(StringUtils.isNotBlank(status.getMime())) {
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, status.getMime()));
        }
        else {
            entity.setContentType(MimeTypeService.DEFAULT_CONTENT_TYPE);
        }
        final FutureHttpResponse target = new FutureHttpResponse() {
            @Override
            public void run() {
                try {
                    status.validate();
                    response = command.call(entity);
                }
                catch(Exception e) {
                    exception = e;
                }
                finally {
                    // For zero byte files #writeTo is never called and the entry latch not triggered
                    entry.countDown();
                    // Continue reading the response
                    exit.countDown();
                }
            }
        };
        final ThreadFactory factory
            = new NamedThreadFactory(String.format("http-%s", file.getName()));
        final Thread t = factory.newThread(target);
        t.start();
        // Wait for output stream to become available
        Uninterruptibles.awaitUninterruptibly(entry);
        if(null != target.getException()) {
            if(target.getException() instanceof BackgroundException) {
                throw (BackgroundException) target.getException();
            }
            throw new DefaultExceptionMappingService().map(target.getException());
        }
        final OutputStream stream = entity.getStream();
        return new HttpResponseOutputStream<R>(stream, attributes, status) {
            @Override
            public void flush() throws IOException {
                stream.flush();
            }

            /**
             * Only available after this stream is closed.
             *
             * @return Response from server for upload
             */
            @Override
            public R getStatus() throws BackgroundException {
                status.validate();
                // Block the calling thread until after the full response from the server
                // has been consumed.
                Uninterruptibles.awaitUninterruptibly(exit);
                if(null != target.getException()) {
                    if(target.getException() instanceof BackgroundException) {
                        throw (BackgroundException) target.getException();
                    }
                    throw new DefaultExceptionMappingService().map(target.getException());
                }
                return target.getResponse();
            }
        };
    }

    @Override
    public abstract HttpResponseOutputStream<R> write(Path file, TransferStatus status, final ConnectionCallback callback) throws BackgroundException;
}
