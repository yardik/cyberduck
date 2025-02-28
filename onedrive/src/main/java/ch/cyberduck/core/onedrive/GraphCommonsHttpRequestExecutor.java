package ch.cyberduck.core.onedrive;

/*
 * Copyright (c) 2002-2017 iterate GmbH. All rights reserved.
 * https://cyberduck.io/
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
 */

import ch.cyberduck.core.http.DelayedHttpEntity;
import ch.cyberduck.core.http.HttpMethodReleaseInputStream;
import ch.cyberduck.core.threading.DefaultThreadPool;
import ch.cyberduck.core.transfer.TransferStatus;

import org.apache.commons.io.input.NullInputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.nuxeo.onedrive.client.RequestExecutor;
import org.nuxeo.onedrive.client.RequestHeader;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.Uninterruptibles;

public abstract class GraphCommonsHttpRequestExecutor implements RequestExecutor {

    private final CloseableHttpClient client;

    public GraphCommonsHttpRequestExecutor(final CloseableHttpClient client) {
        this.client = client;
    }

    @Override
    public Upload doPost(final URL url, final Set<RequestHeader> headers) {
        final HttpEntityEnclosingRequestBase request = new HttpPost(url.toString());
        return this.doUpload(url, headers, request);
    }

    @Override
    public Upload doPut(final URL url, final Set<RequestHeader> headers) {
        final HttpEntityEnclosingRequestBase request = new HttpPut(url.toString());
        return this.doUpload(url, headers, request);
    }

    @Override
    public Upload doPatch(final URL url, final Set<RequestHeader> headers) {
        final HttpPatch request = new HttpPatch(url.toString());
        return this.doUpload(url, headers, request);
    }

    protected Upload doUpload(final URL url, final Set<RequestHeader> headers, final HttpEntityEnclosingRequestBase request) {
        for(RequestHeader header : headers) {
            if(header.getKey().equals(HTTP.TRANSFER_ENCODING)) {
                continue;
            }
            if(header.getKey().equals(HTTP.CONTENT_LEN)) {
                continue;
            }
            request.addHeader(new BasicHeader(header.getKey(), header.getValue()));
        }
        final CountDownLatch entry = new CountDownLatch(1);
        final DelayedHttpEntity entity = new DelayedHttpEntity(entry) {
            @Override
            public long getContentLength() {
                for(RequestHeader header : headers) {
                    if(header.getKey().equals(HTTP.CONTENT_LEN)) {
                        return Long.parseLong(header.getValue());
                    }
                }
                // Content-Encoding: chunked
                return TransferStatus.UNKNOWN_LENGTH;
            }
        };
        request.setEntity(entity);
        final DefaultThreadPool executor = new DefaultThreadPool(String.format("http-%s", url), 1);
        final Future<CloseableHttpResponse> future = executor.execute(new Callable<CloseableHttpResponse>() {
            @Override
            public CloseableHttpResponse call() throws Exception {
                try {
                    return client.execute(request);
                }
                finally {
                    entry.countDown();
                }
            }
        });
        return new Upload() {
            @Override
            public Response getResponse() throws IOException {
                final CloseableHttpResponse response;
                try {
                    response = Uninterruptibles.getUninterruptibly(future);
                }
                catch(ExecutionException e) {
                    Throwables.throwIfInstanceOf(Throwables.getRootCause(e), IOException.class);
                    throw new IOException(e.getCause());
                }
                finally {
                    executor.shutdown(false);
                }
                return new CommonsHttpResponse(response);
            }

            @Override
            public OutputStream getOutputStream() {
                // Await execution of HTTP request to make stream available
                Uninterruptibles.awaitUninterruptibly(entry);
                return entity.getStream();
            }
        };
    }

    @Override
    public Response doGet(final URL url, final Set<RequestHeader> headers) throws IOException {
        final HttpGet request = new HttpGet(url.toString());
        for(RequestHeader header : headers) {
            if(header.getKey().equals(HTTP.TRANSFER_ENCODING)) {
                continue;
            }
            if(header.getKey().equals(HTTP.CONTENT_LEN)) {
                continue;
            }
            request.addHeader(new BasicHeader(header.getKey(), header.getValue()));
        }
        final CloseableHttpResponse response = client.execute(request);
        return new CommonsHttpResponse(response);
    }

    @Override
    public Response doDelete(final URL url, final Set<RequestHeader> headers) throws IOException {
        final HttpDelete request = new HttpDelete(url.toString());
        for(RequestHeader header : headers) {
            if(header.getKey().equals(HTTP.TRANSFER_ENCODING)) {
                continue;
            }
            if(header.getKey().equals(HTTP.CONTENT_LEN)) {
                continue;
            }
            request.addHeader(new BasicHeader(header.getKey(), header.getValue()));
        }
        final CloseableHttpResponse response = client.execute(request);
        return new CommonsHttpResponse(response);
    }

    public HttpClient getClient() {
        return client;
    }

    private static final class CommonsHttpResponse extends Response {
        private final HttpResponse response;

        public CommonsHttpResponse(final HttpResponse response) throws IOException {
            super(null == response.getEntity() ? new NullInputStream(0L) :
                new HttpMethodReleaseInputStream(response));
            this.response = response;
        }

        @Override
        public int getStatusCode() {
            return response.getStatusLine().getStatusCode();
        }

        @Override
        public String getStatusMessage() {
            return response.getStatusLine().getReasonPhrase();
        }

        @Override
        public String getLocation() {
            Header locationHeader = response.getFirstHeader("Location");
            if(null == locationHeader) {
                return null;
            }
            return locationHeader.getValue();
        }

        @Override
        public String getHeader(final String header) {
            if(response.containsHeader(header)) {
                return response.getFirstHeader(header).getValue();
            }
            return null;
        }
    }

    @Override
    public void close() throws IOException {
        client.close();
    }
}
