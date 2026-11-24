package com.melipayamak.rest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient {

	private final HttpClient httpClient;
	private final String username;
	private final String password;
	private final ObjectMapper objectMapper = new ObjectMapper();

	private final String REST_BASE_URL;

	public RestClient(String username, String password) {

		this(username, password,
				HttpClient.newBuilder()
						.connectTimeout(Duration.ofSeconds(10))
						.build(),
				"https://rest.payamak-panel.com/api/");
	}

	public RestClient(
			String username,
			String password,
			HttpClient httpClient,
			String baseRestUrl) {

		this.username = username;
		this.password = password;
		this.httpClient = httpClient;
		this.REST_BASE_URL = baseRestUrl;
	}

	// Build request with credentials and dynamic parameters
	private HttpRequest buildRequest(String endpoint, Map<String, Object> params) throws Exception {

		params.put("username", username);
		params.put("password", password);

		String json = objectMapper.writeValueAsString(params);

		System.out.println("Request JSON: " + json);

		return HttpRequest.newBuilder()
				.uri(URI.create(REST_BASE_URL + endpoint))
				.timeout(Duration.ofSeconds(30))
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(
						json, StandardCharsets.UTF_8))
				.build();
	}

	// Synchronous API call
	private String callApi(String endpoint, Map<String, Object> params) throws Exception {

		HttpRequest request = buildRequest(endpoint, params);

		HttpResponse<String> response = httpClient.send(
				request,
				HttpResponse.BodyHandlers.ofString());

		return handleResponse(response);
	}

	// Asynchronous API call
	private CompletableFuture<String> callApiAsync(String endpoint, Map<String, Object> params) throws Exception {

		HttpRequest request = buildRequest(endpoint, params);

		return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				.thenApply(this::handleResponse);
	}

	// Common response handling
	private String handleResponse(HttpResponse<String> response) {

		if (response.statusCode() < 200 || response.statusCode() >= 300) {

			throw new RuntimeException(
					"API request failed. HTTP status: "
							+ response.statusCode()
							+ ", response: "
							+ response.body());
		}

		return response.body();
	}

	// Public synchronous method
	public String SendSMS(String to, String from, String text, boolean flash) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("text", text);
		params.put("isflash", flash);

		return callApi("SendSMS/SendSMS", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendSMSAsync(String to, String from, String text, boolean flash) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("text", text);
		params.put("isflash", flash);

		return callApiAsync("SendSMS/SendSMS", params);
	}

	public String SendByBaseNumber(String text, String to, int bodyId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("text", text);
		params.put("to", to);
		params.put("bodyId", bodyId);

		return callApi("SendSMS/BaseServiceNumber", params);
	}

	public CompletableFuture<String> SendByBaseNumberAsync(String text, String to, int bodyId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("text", text);
		params.put("to", to);
		params.put("bodyId", bodyId);

		return callApiAsync("SendSMS/BaseServiceNumber", params);
	}

	public String GetDeliveries2(long recId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("recId", recId);

		return callApi("SendSMS/GetDeliveries2", params);
	}

	public CompletableFuture<String> GetDeliveries2Async(long recId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("recId", recId);

		return callApiAsync("SendSMS/GetDeliveries2", params);
	}

	public String GetMessages(int location, String from, String index, boolean count) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("location", location);
		params.put("from", from);
		params.put("index", index);
		params.put("count", count);

		return callApi("SendSMS/GetMessages", params);
	}

	public CompletableFuture<String> GetMessagesAsync(int location, String from, String index, boolean count) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("location", location);
		params.put("from", from);
		params.put("index", index);
		params.put("count", count);

		return callApiAsync("SendSMS/GetMessages", params);
	}

	public String GetCredit() throws Exception {
		return callApi("SendSMS/GetCredit", new LinkedHashMap<>());
	}

	public CompletableFuture<String> GetCreditAsync() throws Exception {
		return callApiAsync("SendSMS/GetCredit", new LinkedHashMap<>());
	}

	public String GetBasePrice() throws Exception {
		return callApi("SendSMS/GetBasePrice", new LinkedHashMap<>());
	}

	public CompletableFuture<String> GetBasePriceAsync() throws Exception {
		return callApiAsync("SendSMS/GetBasePrice", new LinkedHashMap<>());
	}

	public String GetUserNumbers() throws Exception {
		return callApi("SendSMS/GetUserNumbers", new LinkedHashMap<>());
	}

	public CompletableFuture<String> GetUserNumbersAsync() throws Exception {
		return callApiAsync("SendSMS/GetUserNumbers", new LinkedHashMap<>());
	}

}