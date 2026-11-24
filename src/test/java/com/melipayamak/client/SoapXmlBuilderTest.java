package com.melipayamak.client;

import com.melipayamak.soap.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SoapXmlBuilderTest {

    @Test
    void shouldBuildValidSoapRequest() throws Exception {

        SoapXmlBuilder builder =
                new SoapXmlBuilder("test-user", "test-password");

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("to", "09123456789");
        params.put("from", "50001234");
        params.put("text", "Hello World");

        String xml = builder.buildWsReq("SendSimpleSMS2", params);

        assertTrue(xml.contains("SendSimpleSMS2"));
        assertTrue(xml.contains("test-user"));
        assertTrue(xml.contains("test-password"));
        assertTrue(xml.contains("09123456789"));
        assertTrue(xml.contains("Hello World"));
    }

    @Test
    void shouldEscapeXmlSpecialCharacters() throws Exception {

        SoapXmlBuilder builder =
                new SoapXmlBuilder("user", "password");

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("text", "Hello <World> & \"Friends\"");

        String xml = builder.buildWsReq("SendSimpleSMS2", params);

        assertTrue(xml.contains("&lt;World&gt;"));
        assertTrue(xml.contains("&amp;"));
    }

    @Test
    void shouldBuildArrayParameters() throws Exception {

        SoapXmlBuilder builder =
                new SoapXmlBuilder("user", "password");

        Map<String, Object> params = new LinkedHashMap<>();

        params.put("recId",
                new SoapXmlBuilder.SoapArray(
                        "long",
                        java.util.Arrays.asList(100L, 200L, 300L)
                ));

        String xml = builder.buildWsReq("GetDeliveries", params);

        assertTrue(xml.contains("<long>100</long>"));
        assertTrue(xml.contains("<long>200</long>"));
        assertTrue(xml.contains("<long>300</long>"));
    }
}