package com.melipayamak.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletionStage;

public class DefaultHttpTransport implements HttpTransport {

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(java.time.Duration.ofSeconds(30))
            .build();

    @Override
    public CompletionStage<HttpResponse> post(
            String url,
            String contentType,
            String soapAction,
            String body) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", contentType)
                .header("SOAPAction", soapAction)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        return client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(response -> new HttpResponse(
                        response.statusCode(),
                        response.body()));
    }
}