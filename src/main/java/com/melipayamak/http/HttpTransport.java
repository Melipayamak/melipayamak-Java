package com.melipayamak.http;

import java.util.concurrent.CompletionStage;

public interface HttpTransport {
    CompletionStage<HttpResponse> post(
        String url,
        String contentType,
        String soapAction,
        String body
    );
}