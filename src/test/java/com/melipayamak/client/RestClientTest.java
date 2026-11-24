package com.melipayamak.client;

import static org.junit.jupiter.api.Assertions.*;

import java.net.http.HttpClient;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import com.melipayamak.rest.*;

class RestClientTest {

    private MockWebServer mockServer;
    private RestClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception {

        mockServer = new MockWebServer();
        mockServer.start();

        client = new RestClient(
                "testUser",
                "testPassword",
                HttpClient.newHttpClient(),
                mockServer.url("/SendSMS/").toString()
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        mockServer.shutdown();
    }

    @Test
    void shouldSendSmsSuccessfully() throws Exception {

        mockServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"success\":true}")
                .addHeader("Content-Type", "application/json"));

        String response = client.SendSMS(
                "09120000000",
                "1000",
                "Hello",
                false
        );

        assertEquals("{\"success\":true}", response);
    }

    @Test
    void shouldIncludeCredentialsAndParametersInRequest()
            throws Exception {

        mockServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{}"));

        client.SendSMS(
                "09120000000",
                "1000",
                "Hello",
                false
        );

        RecordedRequest request = mockServer.takeRequest();

        assertEquals("POST", request.getMethod());

        JsonNode body = objectMapper.readTree(
                request.getBody().readUtf8()
        );

        assertEquals("testUser", body.get("username").asText());
        assertEquals("testPassword", body.get("password").asText());
        assertEquals("09120000000", body.get("to").asText());
        assertEquals("1000", body.get("from").asText());
        assertEquals("Hello", body.get("text").asText());
        assertFalse(body.get("isflash").asBoolean());
    }

    @Test
    void shouldThrowExceptionForHttpError() {

        mockServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("Internal Server Error"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> client.SendSMS(
                        "09120000000",
                        "1000",
                        "Hello",
                        false
                )
        );

        assertTrue(exception.getMessage().contains("500"));
    }

    @Test
    void shouldSendSmsAsynchronously() throws Exception {

        mockServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"success\":true}"));

        String response = client.SendSMSAsync(
                "09120000000",
                "1000",
                "Hello",
                false
        ).get(5, TimeUnit.SECONDS);

        assertEquals("{\"success\":true}", response);
    }

    @Test
    void shouldPropagateAsyncHttpErrors() {

        mockServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("Internal Server Error"));

        assertThrows(
                ExecutionException.class,
                () -> client.SendSMSAsync(
                        "09120000000",
                        "1000",
                        "Hello",
                        false
                ).get(5, TimeUnit.SECONDS)
        );
    }
}