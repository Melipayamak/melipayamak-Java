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
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient {

	private final HttpClient httpClient;
	private final String username;
	private final String password;
	private final ObjectMapper objectMapper = new ObjectMapper();

	private final String REST_BASE_URL;
	private final String REST_SEND_EP = "SendSMS/";
	private final String REST_SMART_EP = "SmartSMS/";
	private final String REST_SHARED_EP = "ShareService/";
	private final String REST_VOICE_EP = "Voice/";

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

		// System.out.println("Request JSON: " + json);

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
		System.out.println(request.toString());
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

		return callApi(REST_SEND_EP + "SendSMS", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendSMSAsync(String to, String from, String text, boolean flash) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("text", text);
		params.put("isflash", flash);

		return callApiAsync(REST_SEND_EP + "SendSMS", params);
	}

	public String SendByBaseNumber(String text, String to, int bodyId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("text", text);
		params.put("to", to);
		params.put("bodyId", bodyId);

		return callApi(REST_SEND_EP + "BaseServiceNumber", params);
	}

	public CompletableFuture<String> SendByBaseNumberAsync(String text, String to, int bodyId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("text", text);
		params.put("to", to);
		params.put("bodyId", bodyId);

		return callApiAsync(REST_SEND_EP + "BaseServiceNumber", params);
	}

	public String GetDeliveries2(long recId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("recId", recId);

		return callApi(REST_SEND_EP + "GetDeliveries2", params);
	}

	public CompletableFuture<String> GetDeliveries2Async(long recId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("recId", recId);

		return callApiAsync(REST_SEND_EP + "GetDeliveries2", params);
	}

	public String GetMessages(int location, String from, String index, boolean count) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("location", location);
		params.put("from", from);
		params.put("index", index);
		params.put("count", count);

		return callApi(REST_SEND_EP + "GetMessages", params);
	}

	public CompletableFuture<String> GetMessagesAsync(int location, String from, String index, boolean count) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("location", location);
		params.put("from", from);
		params.put("index", index);
		params.put("count", count);

		return callApiAsync(REST_SEND_EP + "GetMessages", params);
	}

	public String GetCredit() throws Exception {
		return callApi(REST_SEND_EP + "GetCredit", new LinkedHashMap<>());
	}

	public CompletableFuture<String> GetCreditAsync() throws Exception {
		return callApiAsync(REST_SEND_EP + "GetCredit", new LinkedHashMap<>());
	}

	public String GetBasePrice() throws Exception {
		return callApi(REST_SEND_EP + "GetBasePrice", new LinkedHashMap<>());
	}

	public CompletableFuture<String> GetBasePriceAsync() throws Exception {
		return callApiAsync(REST_SEND_EP + "GetBasePrice", new LinkedHashMap<>());
	}

	public String GetUserNumbers() throws Exception {
		return callApi(REST_SEND_EP + "GetUserNumbers", new LinkedHashMap<>());
	}

	public CompletableFuture<String> GetUserNumbersAsync() throws Exception {
		return callApiAsync(REST_SEND_EP + "GetUserNumbers", new LinkedHashMap<>());
	}

	// v2
	// Public synchronous method
	public String SendOTP(String to, String from, String code) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("code", code);

		return callApi(REST_SEND_EP + "SendOTP", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendOTPAsync(String to, String from, String code) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("code", code);

		return callApiAsync(REST_SEND_EP + "SendOTP", params);
	}


	// Public synchronous method
	public String SendMultipleSMS(String[] to, String from, String[] text) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("text", text);

		return callApi(REST_SEND_EP + "SendMultipleSMS", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendMultipleSMSAsync(String[] to, String from, String[] text) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("text", text);

		return callApiAsync(REST_SEND_EP + "SendMultipleSMS", params);
	}


	// Public synchronous method
	public String SendSchedule(String to, String from, String text, String scheduleDate, int period) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("text", text);
		params.put("scheduleDate", scheduleDate);
		params.put("period", period);

		return callApi(REST_SEND_EP + "SendSchedule", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendScheduleAsync(String to, String from, String text, String scheduleDate, int period) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("text", text);
		params.put("scheduleDate", scheduleDate);
		params.put("period", period);

		return callApiAsync(REST_SEND_EP + "SendSchedule", params);
	}

	// Public synchronous method
	public String GetSchedule(int scheduleId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("scheduleId", scheduleId);

		return callApi(REST_SEND_EP + "GetSchedule", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetScheduleAsync(int scheduleId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("scheduleId", scheduleId);

		return callApiAsync(REST_SEND_EP + "GetSchedule", params);
	}

	// Public synchronous method
	public String GetDeliveries(String recID) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recID", recID);

		return callApi(REST_SEND_EP + "GetDeliveries", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetDeliveriesAsync(String recID) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recID", recID);

		return callApiAsync(REST_SEND_EP + "GetDeliveries", params);
	}

	// Public synchronous method
	public String GetNewDeliveries(String[] recIds) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recID", recIds);

		return callApi(REST_SEND_EP + "GetNewDeliveries", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetNewDeliveriesAsync(String[] recIds) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recID", recIds);

		return callApiAsync(REST_SEND_EP + "GetNewDeliveries", params);
	}

	// Public synchronous method
	public String GetDeliveries2(String recID) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recID", recID);

		return callApi(REST_SEND_EP + "GetDeliveries2", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetDeliveries2Async(String recID) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recID", recID);

		return callApiAsync(REST_SEND_EP + "GetDeliveries2", params);
	}

	// Public synchronous method
	public String GetInboxCount(boolean isRead) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("isRead", isRead);

		return callApi(REST_SEND_EP + "GetInboxCount", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetInboxCountAsync(boolean isRead) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("isRead", isRead);

		return callApiAsync(REST_SEND_EP + "GetInboxCount", params);
	}

	// Public synchronous method
	public String GetCredit2() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		return callApi(REST_SEND_EP + "GetCredit2", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetCredit2Async() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		return callApiAsync(REST_SEND_EP + "GetCredit2", params);
	}

	// Public synchronous method
	public String GetSmsPrice(int irancellCount, int mciCount, String from, String text) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("irancellCount", irancellCount);
		params.put("mciCount", mciCount);
		params.put("text", text);
		params.put("from", from);

		return callApi(REST_SEND_EP + "GetSmsPrice", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetSmsPriceAsync(int irancellCount, int mciCount, String from, String text) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("irancellCount", irancellCount);
		params.put("mciCount", mciCount);
		params.put("text", text);
		params.put("from", from);

		return callApiAsync(REST_SEND_EP + "GetSmsPrice", params);
	}

	// Public synchronous method
	public String BaseServiceNumberSchedule(String to, String text, int bodyId, String scheduleDate) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("bodyId", bodyId);
		params.put("text", text);
		params.put("scheduleDate", scheduleDate);

		return callApi(REST_SEND_EP + "BaseServiceNumberSchedule", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> BaseServiceNumberScheduleAsync(String to, String text, int bodyId, String scheduleDate) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("bodyId", bodyId);
		params.put("text", text);
		params.put("scheduleDate", scheduleDate);

		return callApiAsync(REST_SEND_EP + "BaseServiceNumberSchedule", params);
	}

	// Public synchronous method
	public String SendSmartSms(String to, String from, String text, String fromSupportOne, String fromSupportTwo) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("text", text);
		params.put("fromSupportOne", fromSupportOne);
		params.put("fromSupportTwo", fromSupportTwo);

		return callApi(REST_SMART_EP + "Send", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendSmartSmsAsync(String to, String from, String text, String fromSupportOne, String fromSupportTwo) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("text", text);
		params.put("fromSupportOne", fromSupportOne);
		params.put("fromSupportTwo", fromSupportTwo);

		return callApiAsync(REST_SMART_EP + "Send", params);
	}

	// Public synchronous method
	public String SendMultipleSmartSms(String[] to, String from, String[] text, String fromSupportOne, String fromSupportTwo) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("text", text);
		params.put("fromSupportOne", fromSupportOne);
		params.put("fromSupportTwo", fromSupportTwo);

		return callApi(REST_SMART_EP + "SendMultiple", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendMultipleSmartSmsAsync(String[] to, String from, String[] text, String fromSupportOne, String fromSupportTwo) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("text", text);
		params.put("fromSupportOne", fromSupportOne);
		params.put("fromSupportTwo", fromSupportTwo);

		return callApiAsync(REST_SMART_EP + "SendMultiple", params);
	}

	// Public synchronous method
	public String GetDeliveries2SmartSms(int id) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("Id", id);

		return callApi(REST_SMART_EP + "GetDeliveries2", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetDeliveries2SmartSmsAsync(int id) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("Id", id);

		return callApiAsync(REST_SMART_EP + "GetDeliveries2", params);
	}

	// Public synchronous method
	public String GetDeliveriesSmartSms(int[] ids) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("Ids", ids);

		return callApi(REST_SMART_EP + "GetDeliveries", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetDeliveriesSmartSmsAsync(int[] ids) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("Ids", ids);

		return callApiAsync(REST_SMART_EP + "GetDeliveries", params);
	}

	// Public synchronous method
	public String AddBody(String title, String body) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("title", title);
		params.put("body", body);

		return callApi(REST_SHARED_EP + "AddBody", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> AddBodyAsync(String title, String body) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("title", title);
		params.put("body", body);

		return callApiAsync(REST_SHARED_EP + "AddBody", params);
	}

	// Public synchronous method
	public String EditBody(int bodyId, String title, String body) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("bodyId", bodyId);
		params.put("title", title);
		params.put("body", body);

		return callApi(REST_SHARED_EP + "EditBody", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> EditBodyAsync(int bodyId, String title, String body) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("bodyId", bodyId);
		params.put("title", title);
		params.put("body", body);

		return callApiAsync(REST_SHARED_EP + "EditBody", params);
	}

	// Public synchronous method
	public String DeleteBody(int bodyId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("bodyId", bodyId);

		return callApi(REST_SHARED_EP + "DeleteBody", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> DeleteBodyAsync(int bodyId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("bodyId", bodyId);

		return callApiAsync(REST_SHARED_EP + "DeleteBody", params);
	}

	// Public synchronous method
	public String GetBodies(String keyword, int status) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("keyword", keyword);
		params.put("status", status);

		return callApi(REST_SHARED_EP + "GetBodies", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetBodiesAsync(String keyword, int status) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("keyword", keyword);
		params.put("status", status);

		return callApiAsync(REST_SHARED_EP + "GetBodies", params);
	}

	// Public synchronous method
	public String SendSpeechText(String to, String text) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("text", text);

		return callApi(REST_VOICE_EP + "SendSpeechText", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendSpeechTextAsync(String to, String text) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("text", text);

		return callApiAsync(REST_VOICE_EP + "SendSpeechText", params);
	}

	// Public synchronous method
	public String SendVoiceOtp(String to, String code) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("code", code);

		return callApi(REST_VOICE_EP + "SendVoiceOtp", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendVoiceOtpAsync(String to, String code) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("code", code);

		return callApiAsync(REST_VOICE_EP + "SendVoiceOtp", params);
	}

	// Public synchronous method
	public String SendSMSWithSpeechText(String to, String from, String smsBody, String speechBody) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("smsBody", smsBody);
		params.put("speechBody", speechBody);

		return callApi(REST_VOICE_EP + "SendSMSWithSpeechText", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendSMSWithSpeechTextAsync(String to, String from, String smsBody, String speechBody) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("smsBody", smsBody);
		params.put("speechBody", speechBody);

		return callApiAsync(REST_VOICE_EP + "SendSMSWithSpeechText", params);
	}

	// Public synchronous method
	public String SendSMSWithSpeechTextBySchduleDate(String to, String from, String smsBody, String speechBody, String scheduleDate) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("smsBody", smsBody);
		params.put("speechBody", speechBody);
		params.put("scheduleDate", scheduleDate);

		return callApi(REST_VOICE_EP + "SendSMSWithSpeechTextBySchduleDate", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendSMSWithSpeechTextBySchduleDateAsync(String to, String from, String smsBody, String speechBody, String scheduleDate) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("to", to);
		params.put("from", from);
		params.put("smsBody", smsBody);
		params.put("speechBody", speechBody);
		params.put("scheduleDate", scheduleDate);

		return callApiAsync(REST_VOICE_EP + "SendSMSWithSpeechTextBySchduleDate", params);
	}

	// Public synchronous method
	public String GetSendSpeechTextStatus(String recId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recId", recId);

		return callApi(REST_VOICE_EP + "GetSendSpeechTextStatus", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetSendSpeechTextStatusAsync(String recId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recId", recId);

		return callApiAsync(REST_VOICE_EP + "GetSendSpeechTextStatus", params);
	}

	// Public synchronous method
	public String GetSendSpeechTextStatuses(String[] recIds) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recIds", recIds);

		return callApi(REST_VOICE_EP + "GetSendSpeechTextStatuses", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetSendSpeechTextStatusesAsync(String[] recIds) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recIds", recIds);

		return callApiAsync(REST_VOICE_EP + "GetSendSpeechTextStatuses", params);
	}

	// Public synchronous method
	public String GetSendSMSWithSpeechTextStatus(String recId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recId", recId);

		return callApi(REST_VOICE_EP + "GetSendSMSWithSpeechTextStatus", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> GetSendSMSWithSpeechTextStatusAsync(String recId) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("recId", recId);

		return callApiAsync(REST_VOICE_EP + "GetSendSMSWithSpeechTextStatus", params);
	}

	// Public synchronous method
	public String SendBulkSpeechText(String title, String body, String receivers, String DateToSend, int RepeatCount) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("title", title);
		params.put("body", body);
		params.put("receivers", receivers);
		params.put("DateToSend", DateToSend);
		params.put("RepeatCount", RepeatCount);

		return callApi(REST_VOICE_EP + "SendBulkSpeechText", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendBulkSpeechTextAsync(String title, String body, String receivers, String DateToSend, int RepeatCount) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("title", title);
		params.put("body", body);
		params.put("receivers", receivers);
		params.put("DateToSend", DateToSend);
		params.put("RepeatCount", RepeatCount);

		return callApiAsync(REST_VOICE_EP + "SendBulkSpeechText", params);
	}

	// Public synchronous method
	public String SendBulkVoiceSMS(String title, int voiceFileId, String receivers, String DateToSend, int repeatCount) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("title", title);
		params.put("voiceFileId", voiceFileId);
		params.put("DateToSend", DateToSend);
		params.put("repeatCount", repeatCount);

		return callApi(REST_VOICE_EP + "SendBulkVoiceSMS", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> SendBulkVoiceSMSAsync(String title, int voiceFileId, String receivers, String DateToSend, int repeatCount) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("title", title);
		params.put("voiceFileId", voiceFileId);
		params.put("DateToSend", DateToSend);
		params.put("repeatCount", repeatCount);

		return callApiAsync(REST_VOICE_EP + "SendBulkVoiceSMS", params);
	}

	// Public synchronous method
	public String UploadVoiceFile(String title, String base64StringFile) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("title", title);
		params.put("base64StringFile", base64StringFile);

		return callApi(REST_VOICE_EP + "UploadVoiceFile", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> UploadVoiceFileAsync(String title, String base64StringFile) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("title", title);
		params.put("base64StringFile", base64StringFile);

		return callApiAsync(REST_VOICE_EP + "UploadVoiceFile", params);
	}

	// Public synchronous method
	public String UploadVoiceFileByte(String title, String file) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("title", title);
		params.put("file", file);

		return callApi(REST_VOICE_EP + "UploadVoiceFileByte", params);
	}

	// Public asynchronous method
	public CompletableFuture<String> UploadVoiceFileByteAsync(String title, String file) throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("title", title);
		params.put("file", file);

		return callApiAsync(REST_VOICE_EP + "UploadVoiceFileByte", params);
	}
}