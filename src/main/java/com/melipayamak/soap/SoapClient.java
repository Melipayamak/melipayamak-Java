package com.melipayamak.soap;

import java.util.concurrent.CompletionStage;

import com.melipayamak.http.HttpResponse;
import com.melipayamak.http.HttpTransport;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class SoapClient {

    private final HttpTransport httpTransport;
    private final SoapXmlBuilder soapBuilder;

    private final String SOAP_SEND_URL = "https://api.payamak-panel.com/post/send.asmx";
    private final String SOAP_RECEIVE_URL = "https://api.payamak-panel.com/post/receive.asmx";
    private final String SOAP_CONTACTS_URL = "https://api.payamak-panel.com/post/contacts.asmx";
    private final String SOAP_ACTIONS_URL = "https://api.payamak-panel.com/post/actions.asmx";
    private final String SOAP_SCHEDULE_URL = "https://api.payamak-panel.com/post/schedule.asmx";
    private final String SOAP_USERS_URL = "https://api.payamak-panel.com/post/users.asmx";
    private final String SOAP_VOICE_URL = "https://api.payamak-panel.com/post/voice.asmx";
    private final String SOAP_NEWBULKS_URL = "https://api.payamak-panel.com/post/newbulks.asmx";
    private final String SOAP_SMART_URL = "https://api.payamak-panel.com/post/Smartsms.asmx";
    private final String SOAP_BLACKLIST_URL = "https://api.payamak-panel.com/post/blacklist.asmx";

    public SoapClient(String username, String password, HttpTransport httpTransport) {
        this.httpTransport = httpTransport;
        soapBuilder = new SoapXmlBuilder(username, password);
    }

    private CompletionStage<HttpResponse> execute(String soapUrl, String soapAction, String requestXml) {

        return httpTransport.post(
                soapUrl,
                "text/xml; charset=utf-8",
                "http://tempuri.org/" + soapAction,
                requestXml);
    }

    public CompletionStage<HttpResponse> SendSimpleSMS2(String to, String from, String text, boolean isflash)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("text", text);
        params.put("from", from);
        params.put("to", to);
        params.put("isflash", isflash);

        String _func = "SendSimpleSMS2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendSimpleSMS(String[] to, String from, String text, Boolean isflash)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", new SoapXmlBuilder.SoapArray("string", Arrays.asList(to)));
        params.put("from", from);
        params.put("text", text);
        params.put("isflash", isflash);

        String _func = "SendSimpleSMS";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendSms(String[] to, String from, String text, boolean isflash, String udh)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", new SoapXmlBuilder.SoapArray("string", Arrays.asList(to)));
        params.put("from", from);
        params.put("text", text);
        params.put("isflash", isflash);
        params.put("udh", udh);

        String _func = "SendSms";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendWithDomain(String to, String from, String text, boolean isflash,
            String domain) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", to);
        params.put("from", from);
        params.put("text", text);
        params.put("isflash", isflash);
        params.put("domain", domain);

        String _func = "SendWithDomain";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendByBaseNumber(String[] text, String to, int bodyId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("text", new SoapXmlBuilder.SoapArray("string", Arrays.asList(text)));
        params.put("to", to);
        params.put("bodyId", bodyId);

        String _func = "SendByBaseNumber";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendByBaseNumber2(String text, String to, int bodyId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("text", text);
        params.put("to", to);
        params.put("bodyId", bodyId);

        String _func = "SendByBaseNumber2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> getMessages(int location, String from, int index, int count) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("location", location);
        params.put("from", from);
        params.put("index", index);
        params.put("count", count);

        String _func = "getMessages";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetSmsPrice(int irancellCount, int mtnCount, String from, String text)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("irancellCount", irancellCount);
        params.put("mtnCount", mtnCount);
        params.put("from", from);
        params.put("text", text);

        String _func = "GetSmsPrice";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMultiDelivery2(String recId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recId", recId);

        String _func = "GetMultiDelivery2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMultiDelivery(String recId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recId", recId);

        String _func = "GetMultiDelivery";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetInboxCount(boolean isRead) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("isRead", isRead);

        String _func = "GetInboxCount";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetDelivery2(String recId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recId", recId);

        String _func = "GetDelivery2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetDelivery(String recId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recId", recId);

        String _func = "GetDelivery";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetDeliveries3(String[] recIds) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recIds", new SoapXmlBuilder.SoapArray("string", Arrays.asList(recIds)));

        String _func = "GetDeliveries3";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetDeliveries2(String recId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recId", recId);

        String _func = "GetDeliveries2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetDeliveries(long[] recIds) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recIds", new SoapXmlBuilder.SoapArray("long", Arrays.asList(recIds)));

        String _func = "GetDeliveries";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetCredit() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetCredit";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    // Receive
    public CompletionStage<HttpResponse> RemoveMessages2(int location, String msgIds) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("location", location);
        params.put("msgIds", msgIds);

        String _func = "RemoveMessages2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    // use 'Received' or 'Sent' or 'Removed' or 'Deleted' for location in the next
    // method
    public CompletionStage<HttpResponse> RemoveMessages(String location, String msgIds) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("location", location);
        params.put("msgIds", msgIds);

        String _func = "RemoveMessages";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUsersMessagesByDate(int location, String from, int index, int count,
            String dateFrom, String dateTo) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("location", location);
        params.put("from", from);
        params.put("index", index);
        params.put("count", count);
        params.put("dateFrom", dateFrom);
        params.put("dateTo", dateTo);

        String _func = "GetUsersMessagesByDate";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetOutBoxCount() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetOutBoxCount";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMessagesWithChangeIsRead(int location, String from, int index, int count,
            boolean isRead, boolean changeIsRead) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("location", location);
        params.put("from", from);
        params.put("index", index);
        params.put("count", count);
        params.put("isRead", isRead);
        params.put("changeIsRead", changeIsRead);

        String _func = "GetMessagesWithChangeIsRead";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMessagesReceptions(int msgId, int fromRows) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("msgId", msgId);
        params.put("fromRows", fromRows);

        String _func = "GetMessagesReceptions";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMessagesFilterByDate(int location, String from, int index, int count,
            String dateFrom, String dateTo, boolean isRead) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("location", location);
        params.put("from", from);
        params.put("index", index);
        params.put("count", count);
        params.put("dateFrom", dateFrom);
        params.put("dateTo", dateTo);
        params.put("isRead", isRead);

        String _func = "GetMessagesFilterByDate";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMessagesByDate(int location, String from, int index, int count,
            String dateFrom, String dateTo) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("location", location);
        params.put("from", from);
        params.put("index", index);
        params.put("count", count);
        params.put("dateFrom", dateFrom);
        params.put("dateTo", dateTo);

        String _func = "GetMessagesByDate";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMessagesAfterIDJson(int location, String from, int count, int msgId)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("location", location);
        params.put("from", from);
        params.put("count", count);
        params.put("msgId", msgId);

        String _func = "GetMessagesAfterIDJson";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMessagesAfterID(int location, String from, int count, int msgId)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("location", location);
        params.put("from", from);
        params.put("count", count);
        params.put("msgId", msgId);

        String _func = "GetMessagesAfterID";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMessages(int location, String from, int index, int count) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("location", location);
        params.put("from", from);
        params.put("index", index);
        params.put("count", count);

        String _func = "GetMessages";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMessageStr(int location, String from, int index, int count)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("location", location);
        params.put("from", from);
        params.put("index", index);
        params.put("count", count);

        String _func = "GetMessageStr";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> ChangeMessageIsRead(String msgIds) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("msgIds", msgIds);

        String _func = "ChangeMessageIsRead";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_RECEIVE_URL, _func, wsReq);
    }

    // Users
    public CompletionStage<HttpResponse> AddPayment(String name, String family, String bankName, String code,
            double amount, String cardNumber) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("name", name);
        params.put("family", family);
        params.put("bankName", bankName);
        params.put("code", code);
        params.put("amount", amount);
        params.put("cardNumber", cardNumber);

        String _func = "AddPayment";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddUser(int productId, String descriptions, String mobileNumber,
            String emailAddress, String nationalCode, String name, String family, String corporation, String phone,
            String fax, String address, String postalCode, String certificateNumber) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("productId", productId);
        params.put("descriptions", descriptions);
        params.put("mobileNumber", mobileNumber);
        params.put("emailAddress", emailAddress);
        params.put("nationalCode", nationalCode);
        params.put("name", name);
        params.put("family", family);
        params.put("corporation", corporation);
        params.put("phone", phone);
        params.put("fax", fax);
        params.put("address", address);
        params.put("postalCode", postalCode);
        params.put("certificateNumber", certificateNumber);

        String _func = "AddUser";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddUserComplete(int productId, String descriptions, String mobileNumber,
            String emailAddress, String nationalCode, String name, String family, String corporation, String phone,
            String fax, String address, String postalCode, String certificateNumber, int country, int province,
            int city, String howFindUs, String commercialCode, String saleId, String recommanderId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("productId", productId);
        params.put("descriptions", descriptions);
        params.put("mobileNumber", mobileNumber);
        params.put("emailAddress", emailAddress);
        params.put("nationalCode", nationalCode);
        params.put("name", name);
        params.put("family", family);
        params.put("corporation", corporation);
        params.put("phone", phone);
        params.put("fax", fax);
        params.put("address", address);
        params.put("postalCode", postalCode);
        params.put("certificateNumber", certificateNumber);
        params.put("country", country);
        params.put("province", province);
        params.put("city", city);
        params.put("howFindUs", howFindUs);
        params.put("commercialCode", commercialCode);
        params.put("saleId", saleId);
        params.put("recommanderId", recommanderId);

        String _func = "AddUserComplete";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddUserWithLocation(int productId, String descriptions, String mobileNumber,
            String emailAddress, String nationalCode, String name, String family, String corporation, String phone,
            String fax, String address, String postalCode, String certificateNumber, int country, int province,
            int city) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("productId", productId);
        params.put("descriptions", descriptions);
        params.put("mobileNumber", mobileNumber);
        params.put("emailAddress", emailAddress);
        params.put("nationalCode", nationalCode);
        params.put("name", name);
        params.put("family", family);
        params.put("corporation", corporation);
        params.put("phone", phone);
        params.put("fax", fax);
        params.put("address", address);
        params.put("postalCode", postalCode);
        params.put("certificateNumber", certificateNumber);
        params.put("country", country);
        params.put("province", province);
        params.put("city", city);

        String _func = "AddUserWithLocation";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddUserWithMobileNumber(int productId, String mobileNumber, String firstName,
            String lastName, String email) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("productId", productId);
        params.put("mobileNumber", mobileNumber);
        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("email", email);

        String _func = "AddUserWithMobileNumber";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddUserWithUserNameAndPass(String targetUserName, String targetUserPassword,
            int productId, String descriptions, String mobileNumber, String emailAddress, String nationalCode,
            String name, String family, String corporation, String phone, String fax, String address, String postalCode,
            String certificateNumber) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("targetUserName", targetUserName);
        params.put("targetUserPassword", targetUserPassword);
        params.put("productId", productId);
        params.put("descriptions", descriptions);
        params.put("mobileNumber", mobileNumber);
        params.put("emailAddress", emailAddress);
        params.put("nationalCode", nationalCode);
        params.put("name", name);
        params.put("family", family);
        params.put("corporation", corporation);
        params.put("phone", phone);
        params.put("fax", fax);
        params.put("address", address);
        params.put("postalCode", postalCode);
        params.put("certificateNumber", certificateNumber);

        String _func = "AddUserWithUserNameAndPass";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AuthenticateUser() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "AuthenticateUser";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> ChangeUserCredit(double amount, String description, String targetUsername,
            boolean GetTax) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("amount", amount);
        params.put("description", description);
        params.put("targetUsername", targetUsername);
        params.put("GetTax", GetTax);

        String _func = "ChangeUserCredit";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> ChangeUserCredit2(String amount, String description, String targetUsername,
            boolean GetTax) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("amount", amount);
        params.put("description", description);
        params.put("targetUsername", targetUsername);
        params.put("GetTax", GetTax);

        String _func = "ChangeUserCredit2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> ChangeUserCreditBySeccondPass(String seccondPassword, double amount,
            String description, String targetUsername, boolean GetTax) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("seccondPassword", seccondPassword);
        params.put("amount", amount);
        params.put("description", description);
        params.put("targetUsername", targetUsername);
        params.put("GetTax", GetTax);

        String _func = "ChangeUserCreditBySeccondPass";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> DeductUserCredit(double amount, String description) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("amount", amount);
        params.put("description", description);

        String _func = "DeductUserCredit";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> ForgotPassword(String mobileNumber, String emailAddress, String targetUsername)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("mobileNumber", mobileNumber);
        params.put("emailAddress", emailAddress);
        params.put("targetUsername", targetUsername);

        String _func = "ForgotPassword";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetCities(int provinceId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("provinceId", provinceId);

        String _func = "GetCities";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetEnExpireDate() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetEnExpireDate";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetExpireDate() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetExpireDate";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetProvinces() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetProvinces";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUserBasePrice(String targetUsername) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("targetUsername", targetUsername);

        String _func = "GetUserBasePrice";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUserByUserID(String pass, int userId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("userId", userId);
        params.put("pass", pass);

        String _func = "GetUserByUserID";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUserCredit(String targetUsername) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("targetUsername", targetUsername);

        String _func = "GetUserCredit";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUserCreditBySecondPass(String secondPassword, String targetUsername)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("targetUsername", targetUsername);
        params.put("secondPassword", secondPassword);

        String _func = "GetUserCreditBySecondPass";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUserDetails(String targetUsername) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("targetUsername", targetUsername);
        String _func = "GetUserDetails";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUserIsExist(String targetUsername) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("targetUsername", targetUsername);

        String _func = "GetUserIsExist";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUserNumbers() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetUserNumbers";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUserTransactions(String targetUsername, String creditType, String dateFrom,
            String dateTo, String keyword) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("targetUsername", targetUsername);
        params.put("creditType", creditType);
        params.put("dateFrom", dateFrom);
        params.put("dateTo", dateTo);
        params.put("keyword", keyword);

        String _func = "GetUserTransactions";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUserWallet() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetUserWallet";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUserWalletTransaction(String dateFrom, String dateTo, int count,
            int startIndex, String payType, String PayLoc) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("dateFrom", dateFrom);
        params.put("dateTo", dateTo);
        params.put("count", count);
        params.put("startIndex", startIndex);
        params.put("payType", payType);
        params.put("PayLoc", PayLoc);

        String _func = "GetUserWalletTransaction";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUsers() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetUsers";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> HasFilter(String text) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("text", text);

        String _func = "HasFilter";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> RemoveUser(String targetUsername) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("targetUsername", targetUsername);

        String _func = "RemoveUser";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> RevivalUser(String targetUsername) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("targetUsername", targetUsername);

        String _func = "RevivalUser";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }

    // Contacts
    public CompletionStage<HttpResponse> AddContact(String groupIds, String firstname, String lastname, String nickname,
            String corporation, String mobilenumber, String phone, String fax, String birthdate, String email,
            int gender, int province, int city, String address, String postalCode, String additionaldate,
            String additionaltext, String descriptions) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("groupIds", groupIds);
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("nickname", nickname);
        params.put("corporation", corporation);
        params.put("mobilenumber", mobilenumber);
        params.put("phone", phone);
        params.put("fax", fax);
        params.put("birthdate", birthdate);
        params.put("email", email);
        params.put("gender", gender);
        params.put("province", province);
        params.put("city", city);
        params.put("address", address);
        params.put("postalCode", postalCode);
        params.put("additionaldate", additionaldate);
        params.put("additionaltext", additionaltext);
        params.put("descriptions", descriptions);

        String _func = "AddContact";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddContactEvents(int contactId, String eventName, int eventType,
            String eventDate) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("contactId", contactId);
        params.put("eventName", eventName);
        params.put("eventType", eventType);
        params.put("eventDate", eventDate);

        String _func = "AddContactEvents";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddGroup(String groupName, String Descriptions, boolean showToChilds)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("groupName", groupName);
        params.put("Descriptions", Descriptions);
        params.put("showToChilds", showToChilds);

        String _func = "AddGroup";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> ChangeContact(int contactId, String mobilenumber, String firstname,
            String lastname, String nickname, String corporation, String phone, String fax, String email, int gender,
            int province, int city, String address, String postalCode, String additionaltext, String descriptions,
            int contactStatus) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("contactId", contactId);
        params.put("mobilenumber", mobilenumber);
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("nickname", nickname);
        params.put("corporation", corporation);
        params.put("phone", phone);
        params.put("fax", fax);
        params.put("email", email);
        params.put("gender", gender);
        params.put("province", province);
        params.put("city", city);
        params.put("address", address);
        params.put("postalCode", postalCode);
        params.put("additionaltext", additionaltext);
        params.put("descriptions", descriptions);
        params.put("contactStatus", contactStatus);

        String _func = "ChangeContact";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> ChangeGroup(int groupId, String groupName, String Descriptions,
            boolean showToChilds, int groupStatus) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("groupId", groupId);
        params.put("groupName", groupName);
        params.put("Descriptions", Descriptions);
        params.put("showToChilds", showToChilds);
        params.put("groupStatus", groupStatus);

        String _func = "ChangeGroup";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> CheckMobileExistInContact(String mobileNumber) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("mobileNumber", mobileNumber);

        String _func = "CheckMobileExistInContact";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetContactEvents(int contactId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("contactId", contactId);

        String _func = "GetContactEvents";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetContacts(int groupId, String keyword, int from, int count)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("groupId", groupId);
        params.put("keyword", keyword);
        params.put("from", from);
        params.put("count", count);

        String _func = "GetContacts";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetContactsByID(int contactId, int status) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("contactId", contactId);
        params.put("status", status);

        String _func = "GetContactsByID";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetGroups() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetGroups";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> MergeGroups(int originGroupId, int destinationGroupId,
            boolean deleteOriginGroup) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("originGroupId", originGroupId);
        params.put("destinationGroupId", destinationGroupId);
        params.put("deleteOriginGroup", deleteOriginGroup);

        String _func = "MergeGroups";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> RemoveContact(String mobilenumber) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("mobilenumber", mobilenumber);

        String _func = "RemoveContact";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> RemoveContactByContactID(int contactId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("contactId", contactId);

        String _func = "RemoveContactByContactID";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> RemoveGroup(int groupId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("groupId", groupId);

        String _func = "RemoveGroup";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    // Actions
    public CompletionStage<HttpResponse> AddBranch(String branchName, int owner) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("branchName", branchName);
        params.put("owner", owner);

        String _func = "AddBranch";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddBulk(String from, int branch, int bulkType, String title, String message,
            String rangeFrom, String rangeTo, String DateToSend, int requestCount, int rowFrom) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("from", from);
        params.put("branch", branch);
        params.put("bulkType", bulkType);
        params.put("title", title);
        params.put("message", message);
        params.put("rangeFrom", rangeFrom);
        params.put("rangeTo", rangeTo);
        params.put("DateToSend", DateToSend);
        params.put("requestCount", requestCount);
        params.put("rowFrom", rowFrom);

        String _func = "AddBulk";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddBulk2(String from, int branch, int bulkType, String title, String message,
            String rangeFrom, String rangeTo, String DateToSend, int requestCount, int rowFrom) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("from", from);
        params.put("branch", branch);
        params.put("bulkType", bulkType);
        params.put("title", title);
        params.put("message", message);
        params.put("rangeFrom", rangeFrom);
        params.put("rangeTo", rangeTo);
        params.put("DateToSend", DateToSend);
        params.put("requestCount", requestCount);
        params.put("rowFrom", rowFrom);

        String _func = "AddBulk2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddNewBulk(String from, int branch, int bulkType, String title, String message,
            String rangeFrom, String rangeTo, String DateToSend, int requestCount, int rowFrom) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("from", from);
        params.put("branch", branch);
        params.put("bulkType", bulkType);
        params.put("title", title);
        params.put("message", message);
        params.put("rangeFrom", rangeFrom);
        params.put("rangeTo", rangeTo);
        params.put("DateToSend", DateToSend);
        params.put("requestCount", requestCount);
        params.put("rowFrom", rowFrom);

        String _func = "AddNewBulk";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddNumber(int branchId, String[] mobileNumbers) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("mobileNumbers", new SoapXmlBuilder.SoapArray("string", Arrays.asList(mobileNumbers)));
        params.put("branchId", branchId);

        String _func = "AddNumber";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetBranchs(int owner) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("owner", owner);

        String _func = "GetBranchs";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetBulk() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetBulk";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetBulkCount(int branch, String rangeFrom, String rangeTo) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("branch", branch);
        params.put("rangeFrom", rangeFrom);
        params.put("rangeTo", rangeTo);

        String _func = "GetBulkCount";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetBulkReceptions(int bulkId, int fromRows) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("bulkId", bulkId);
        params.put("fromRows", fromRows);

        String _func = "GetBulkReceptions";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetBulkStatus(int bulkId, int sent, int failed, int status) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("bulkId", bulkId);
        params.put("sent", sent);
        params.put("failed", failed);
        params.put("status", status);

        String _func = "GetBulkStatus";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMessagesReceptions(long msgId, int fromRows) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("msgId", msgId);
        params.put("fromRows", fromRows);

        String _func = "GetMessagesReceptions";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMobileCount(int branch, String rangeFrom, String rangeTo) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("branch", branch);
        params.put("rangeFrom", rangeFrom);
        params.put("rangeTo", rangeTo);

        String _func = "GetMobileCount";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetSendBulk() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetSendBulk";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetTodaySent() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetTodaySent";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetTotalSent() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetTotalSent";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> RemoveBranch(int branchId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("branchId", branchId);

        String _func = "RemoveBranch";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> RemoveBulk(int bulkId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("bulkId", bulkId);

        String _func = "RemoveBulk";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendMultipleSMS(String[] to, String from, String[] text, boolean isflash,
            String udh) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", new SoapXmlBuilder.SoapArray("string", Arrays.asList(to)));
        params.put("text", new SoapXmlBuilder.SoapArray("string", Arrays.asList(text)));
        params.put("from", from);
        params.put("isflash", isflash);
        params.put("udh", udh);

        String _func = "SendMultipleSMS";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendMultipleSMS2(String[] to, String[] from, String[] text, boolean isflash,
            String udh) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", new SoapXmlBuilder.SoapArray("string", Arrays.asList(to)));
        params.put("from", new SoapXmlBuilder.SoapArray("string", Arrays.asList(from)));
        params.put("text", new SoapXmlBuilder.SoapArray("string", Arrays.asList(text)));
        params.put("isflash", isflash);
        params.put("udh", udh);

        String _func = "SendMultipleSMS2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> UpdateBulkDelivery(int bulkId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("bulkId", bulkId);

        String _func = "UpdateBulkDelivery";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    // Schedule
    public CompletionStage<HttpResponse> AddMultipleSchedule(String[] to, String from, String[] text, boolean isflash,
            String[] scheduleDateTime, String period) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", new SoapXmlBuilder.SoapArray("string", Arrays.asList(to)));
        params.put("text", new SoapXmlBuilder.SoapArray("string", Arrays.asList(text)));
        params.put("scheduleDateTime", new SoapXmlBuilder.SoapArray("dateTime", Arrays.asList(scheduleDateTime)));
        params.put("from", from);
        params.put("period", period);
        params.put("isflash", isflash);

        String _func = "AddMultipleSchedule";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SCHEDULE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddNewUsance(String to, String from, String text, boolean isflash,
            String scheduleStartDateTime, int countrepeat, String scheduleEndDateTime, String periodType)
            throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", to);
        params.put("from", from);
        params.put("text", text);
        params.put("isflash", isflash);
        params.put("scheduleStartDateTime", scheduleStartDateTime);
        params.put("countrepeat", countrepeat);
        params.put("scheduleEndDateTime", scheduleEndDateTime);
        params.put("periodType", periodType);

        String _func = "AddNewUsance";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SCHEDULE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddSchedule(String to, String from, String text, boolean isflash,
            String scheduleDateTime, String period) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", to);
        params.put("from", from);
        params.put("text", text);
        params.put("isflash", isflash);
        params.put("scheduleDateTime", scheduleDateTime);

        String _func = "AddSchedule";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SCHEDULE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddUsance(String to, String from, String text, boolean isflash,
            String scheduleStartDateTime, int repeatAfterDays, String scheduleEndDateTime) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", to);
        params.put("from", from);
        params.put("text", text);
        params.put("isflash", isflash);
        params.put("scheduleStartDateTime", scheduleStartDateTime);
        params.put("repeatAfterDays", repeatAfterDays);
        params.put("scheduleEndDateTime", scheduleEndDateTime);

        String _func = "AddUsance";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SCHEDULE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetScheduleStatus(int scheduleId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("scheduleId", scheduleId);

        String _func = "GetScheduleStatus";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SCHEDULE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> RemoveSchedule(int scheduleId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("scheduleId", scheduleId);

        String _func = "RemoveSchedule";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SCHEDULE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> RemoveScheduleList(int[] scheduleIdList) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("scheduleIdList", new SoapXmlBuilder.SoapArray("int", Arrays.asList(scheduleIdList)));

        String _func = "RemoveScheduleList";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SCHEDULE_URL, _func, wsReq);
    }

    // Voice API Operations
    public CompletionStage<HttpResponse> GetSendSMSWithSpeechTextStatus(int recId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recId", recId);

        String _func = "GetSendSMSWithSpeechTextStatus";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_VOICE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendBulkSpeechText(String title, String body, String receivers,
            String DateToSend, int repeatCount) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("title", title);
        params.put("body", body);
        params.put("receivers", receivers);
        params.put("DateToSend", DateToSend);
        params.put("repeatCount", repeatCount);

        String _func = "SendBulkSpeechText";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_VOICE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendBulkVoiceSMS(String title, int voiceFileId, String receivers,
            String DateToSend, int repeatCount) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("title", title);
        params.put("voiceFileId", voiceFileId);
        params.put("receivers", receivers);
        params.put("DateToSend", DateToSend);
        params.put("repeatCount", repeatCount);

        String _func = "SendBulkVoiceSMS";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_VOICE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendSMSWithSpeechText(String smsBody, String speechBody, String from,
            String to) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("smsBody", smsBody);
        params.put("speechBody", speechBody);
        params.put("from", from);
        params.put("to", to);

        String _func = "SendSMSWithSpeechText";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_VOICE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendSMSWithSpeechTextBySchduleDate(String smsBody, String speechBody,
            String from, String to, String scheduleDate) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("smsBody", smsBody);
        params.put("speechBody", speechBody);
        params.put("from", from);
        params.put("to", to);
        params.put("scheduleDate", scheduleDate);

        String _func = "SendSMSWithSpeechTextBySchduleDate";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_VOICE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> UploadVoiceFile(String title, String base64StringFile) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("title", title);
        params.put("base64StringFile", base64StringFile);

        String _func = "UploadVoiceFile";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_VOICE_URL, _func, wsReq);
    }

    // v2
    public CompletionStage<HttpResponse> SendByBaseNumber3(String text, String to) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("text", text);
        params.put("to", to);

        String _func = "SendByBaseNumber3";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendByBaseNumber4(String text, String to, int bodyId, double smsPrice) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("text", text);
        params.put("to", to);
        params.put("bodyId", bodyId);
        params.put("smsPrice", smsPrice);

        String _func = "SendByBaseNumber4";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendOtp2(String to, String from, String code) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", to);
        params.put("from", from);
        params.put("code", code);

        String _func = "SendOtp2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendSms2(String[] to, String from, String text, boolean isflash, String udh) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", new SoapXmlBuilder.SoapArray("string", Arrays.asList(to)));
        params.put("from", from);
        params.put("text", text);
        params.put("isflash", isflash);
        params.put("udh", udh);

        String _func = "SendSms2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SEND_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddContact2(String groupIds, String firstname, String lastname, String gender, String corporation, String mobilenumber) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("groupIds", groupIds);
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("gender", gender);
        params.put("corporation", corporation);
        params.put("mobilenumber", mobilenumber);

        String _func = "AddContact2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddContactEvents(int contactId, String eventName, String eventType, String eventDate) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("contactId", contactId);
        params.put("eventName", eventName);
        params.put("eventType", eventType);
        params.put("eventDate", eventDate);

        String _func = "AddContactEvents";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> ChangeContact2(int contactId, String mobilenumber, String firstname, String lastname, String nickname, String corporation, String phone, String fax, String email, String gender, int province, int city, String address, String postalCode, String additionaltext, String descriptions, int contactStatus, String birthdate) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("contactId", contactId);
        params.put("mobilenumber", mobilenumber);
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("nickname", nickname);
        params.put("corporation", corporation);
        params.put("phone", phone);
        params.put("fax", fax);
        params.put("email", email);
        params.put("gender", gender);
        params.put("province", province);
        params.put("city", city);
        params.put("address", address);
        params.put("postalCode", postalCode);
        params.put("additionaltext", additionaltext);
        params.put("descriptions", descriptions);
        params.put("contactStatus", contactStatus);
        params.put("birthdate", birthdate);

        String _func = "ChangeContact2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> ChangeGroup(int groupId, String groupName, String Descriptions, boolean showToChilds, String groupStatus) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("groupId", groupId);
        params.put("groupName", groupName);
        params.put("Descriptions", Descriptions);
        params.put("showToChilds", showToChilds);
        params.put("groupStatus", groupStatus);

        String _func = "ChangeGroup";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> RemoveContact2(int contactId, int groupId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("contactId", contactId);
        params.put("groupId", groupId);

        String _func = "RemoveContact2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_CONTACTS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetMultipleSMS3Deliveries(long[] ids) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("Id", new SoapXmlBuilder.SoapArray("long", Arrays.asList(ids)));

        String _func = "GetMultipleSMS3Deliveries";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_ACTIONS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddScheduleSendByBaseNumber2(String text, String to, int bodyId, String scheduleDate) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("text", text);
        params.put("to", to);
        params.put("bodyId", bodyId);
        params.put("scheduleDate", scheduleDate);

        String _func = "AddScheduleSendByBaseNumber2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SCHEDULE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetScheduleDetails(int scheduleId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("scheduleId", scheduleId);

        String _func = "GetScheduleDetails";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SCHEDULE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetSendSpeechTextStatus(long recId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recId", recId);

        String _func = "GetSendSpeechTextStatus";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_VOICE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetSendSpeechTextStatuses(long[] recIds) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recIds", new SoapXmlBuilder.SoapArray("long", Arrays.asList(recIds)));

        String _func = "GetSendSpeechTextStatuses";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_VOICE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendSpeechText(String text, String to) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("text", text);
        params.put("to", to);

        String _func = "SendSpeechText";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_VOICE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendVoiceOtp(String to, String code) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", to);
        params.put("code", code);

        String _func = "SendVoiceOtp";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_VOICE_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddNumberBulk(String from, String title, String message, String receivers, String DateToSend) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("from", from);
        params.put("title", title);
        params.put("message", message);
        params.put("receivers", receivers);
        params.put("DateToSend", DateToSend);

        String _func = "AddNumberBulk";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_NEWBULKS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddNumberBulk2(String from, String body, String[] to) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", new SoapXmlBuilder.SoapArray("string", Arrays.asList(to)));
        params.put("from", from);
        params.put("body", body);

        String _func = "AddNumberBulk2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_NEWBULKS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetBulkDeliveries(long[] recIds) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recIds", new SoapXmlBuilder.SoapArray("long", Arrays.asList(recIds)));

        String _func = "GetBulkDeliveries";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_NEWBULKS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetBulkDeliveries2(String recId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recId", recId);

        String _func = "GetBulkDeliveries2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_NEWBULKS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetBulkDetails(int bulkdId) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("bulkdId", bulkdId);

        String _func = "GetBulkDetails";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_NEWBULKS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendSmsToContact(String message, String title, String from, String groupId, String dateToSend) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("message", message);
        params.put("title", title);
        params.put("from", from);
        params.put("groupId", groupId);
        params.put("dateToSend", dateToSend);

        String _func = "SendSmsToContact";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_NEWBULKS_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetSmartSMSDeliveries(long[] ids) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("Id", new SoapXmlBuilder.SoapArray("long", Arrays.asList(ids)));

        String _func = "GetSmartSMSDeliveries";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SMART_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetSmartSMSDetails(long id) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("Id", id);

        String _func = "GetSmartSMSDetails";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SMART_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendMultipleSmartSMS(String[] to, String[] text, String from, String fromSupportOne, String fromSupportTwo) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", new SoapXmlBuilder.SoapArray("string", Arrays.asList(to)));
        params.put("text", new SoapXmlBuilder.SoapArray("string", Arrays.asList(text)));
        params.put("from", from);
        params.put("fromSupportOne", fromSupportOne);
        params.put("fromSupportTwo", fromSupportTwo);

        String _func = "SendMultipleSmartSMS";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SMART_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> SendSmartSMS(String to, String text, String from, String fromSupportOne, String fromSupportTwo) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("to", to);
        params.put("text", text);
        params.put("from", from);
        params.put("fromSupportOne", fromSupportOne);
        params.put("fromSupportTwo", fromSupportTwo);

        String _func = "SendSmartSMS";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_SMART_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddBlackList(String number, int _type) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("number", number);
        params.put("type", _type);

        String _func = "AddBlackList";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_BLACKLIST_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> AddMobile(int listId, int _type, String[] mobileNumbers, boolean addBySms) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("mobileNumbers", new SoapXmlBuilder.SoapArray("string", Arrays.asList(mobileNumbers)));
        params.put("listId", listId);
        params.put("type", _type);
        params.put("addBySms", addBySms);

        String _func = "AddMobile";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_BLACKLIST_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetBlackLists() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetBlackLists";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_BLACKLIST_URL, _func, wsReq);
    }

    public CompletionStage<HttpResponse> GetUserCredit2() throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();

        String _func = "GetUserCredit2";

        String wsReq = soapBuilder.buildWsReq(_func, params);
        return execute(SOAP_USERS_URL, _func, wsReq);
    }    
}
