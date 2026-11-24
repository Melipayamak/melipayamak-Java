package com.melipayamak.client;

import org.junit.jupiter.api.Test;

import com.melipayamak.http.HttpResponse;
import com.melipayamak.http.HttpTransport;
import com.melipayamak.soap.SoapClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.jupiter.api.Assertions.*;

class SoapClientTest {

    @Test
    void shouldSendSoapRequestThroughHttpTransport() throws Exception {

        FakeHttpTransport transport = new FakeHttpTransport();

        SoapClient client = new SoapClient(
                "test-user",
                "test-password",
                transport);

        CompletionStage<?> result = client.SendSimpleSMS2(
                "09123456789",
                "50001234",
                "Hello",
                false);

        assertNotNull(result);

        assertNotNull(transport.requestedUrl);
        assertNotNull(transport.requestedBody);

        assertTrue(transport.requestedBody.contains("09123456789"));
        assertTrue(transport.requestedBody.contains("Hello"));
    }

    @Test
    void shouldIncludeMultiplePhoneNumbersInSoapRequest() throws Exception {

        FakeHttpTransport transport = new FakeHttpTransport();

        SoapClient client = new SoapClient(
                "test-user",
                "test-password",
                transport);

        String[] phoneNumbers = {
                "0912345678",
                "09123456789"
        };

        client.SendSimpleSMS(
                phoneNumbers,
                "300088103373",
                "Hello from my Java SDK!",
                false);

        String xml = transport.requestedBody;

        assertNotNull(xml);

        assertTrue(xml.contains("<to>"));
        assertTrue(xml.contains("<string>0912345678</string>"));
        assertTrue(xml.contains("<string>09123456789</string>"));

        assertTrue(xml.contains("</to>"));

        // Ensure both numbers appear exactly once.
        assertEquals(1, countOccurrences(xml, "0912345678</string>"));
        assertEquals(1, countOccurrences(xml, "09123456789</string>"));
    }

    private int countOccurrences(String text, String search) {
        return (text.length() - text.replace(search, "").length()) / search.length();
    }
}

class FakeHttpTransport implements HttpTransport {

    public String requestedUrl;
    public String requestedBody;
    public String requestedSoapAction;

    @Override
    public CompletionStage<HttpResponse> post(
            String url,
            String contentType,
            String soapAction,
            String body) {

        this.requestedUrl = url;
        this.requestedBody = body;
        this.requestedSoapAction = soapAction;

        return CompletableFuture.completedFuture(
                new HttpResponse(200, "<response>Success</response>"));
    }
}