/**
 * Copyright (C) 2012 Ness Computing, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nesscomputing.httpclient.response;


import org.junit.Assert;
import org.junit.Before;

import com.nesscomputing.httpclient.AbstractTestHttpClient;
import com.nesscomputing.httpclient.HttpClient;
import com.nesscomputing.httpclient.HttpClientRequest;
import com.nesscomputing.httpclient.response.ContentResponseHandler;
import com.nesscomputing.httpclient.response.StringContentConverter;
import com.nesscomputing.httpclient.testsupport.LocalHttpService;

public class TestStringContentConverter extends AbstractTestHttpClient {

    @Before
    public void setup() {
        Assert.assertNull(localHttpService);
        Assert.assertNull(httpClient);

        localHttpService = LocalHttpService.forHandler(testHandler);
        localHttpService.start();

        httpClient = new HttpClient().start();
    }

    @Override
    protected HttpClientRequest<String> getRequest() {
        final String uri = "http://" + localHttpService.getHost() + ":" + localHttpService.getPort() + "/data";
        return httpClient.get(uri, new ContentResponseHandler<String>(new StringContentConverter())).request();
    }
}
