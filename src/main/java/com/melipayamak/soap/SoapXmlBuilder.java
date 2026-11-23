package com.melipayamak.soap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SoapXmlBuilder {

    public final String Namespace = "http://tempuri.org/";
    private final String username;
    private final String password;

    public SoapXmlBuilder(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static class SoapArray {
        private final String childTag;
        private final Iterable<?> values;

        public SoapArray(String childTag, Iterable<?> values) {
            this.childTag = childTag;
            this.values = values;
        }
    }

    public String buildWsReq(String operation, Map<String, Object> parameters) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        Document document = factory.newDocumentBuilder().newDocument();

        // SOAP Envelope
        Element envelope = document.createElementNS(
                "http://schemas.xmlsoap.org/soap/envelope/",
                "soap:Envelope");

        envelope.setAttributeNS(
                "http://www.w3.org/2000/xmlns/",
                "xmlns:soap",
                "http://schemas.xmlsoap.org/soap/envelope/");

        document.appendChild(envelope);

        // SOAP Body
        Element body = document.createElementNS(
                "http://schemas.xmlsoap.org/soap/envelope/",
                "soap:Body");

        envelope.appendChild(body);

        // Operation
        Element operationElement = document.createElementNS(
                Namespace,
                operation);

        body.appendChild(operationElement);

        // Parameters
        Map<String, Object> allParameters = new LinkedHashMap<>();

        if (parameters != null) {
            allParameters.putAll(parameters);
        }

        allParameters.put("username", username);
        allParameters.put("password", password);

        for (Map.Entry<String, Object> parameter : allParameters.entrySet()) {

            Element parameterElement = document.createElement(parameter.getKey());
            operationElement.appendChild(parameterElement);

            Object value = parameter.getValue();

            if (value == null) {
                parameterElement.setAttributeNS(
                        "http://www.w3.org/2001/XMLSchema-instance",
                        "xsi:nil",
                        "true");

                parameterElement.setAttributeNS(
                        "http://www.w3.org/2000/xmlns/",
                        "xmlns:xsi",
                        "http://www.w3.org/2001/XMLSchema-instance");

                continue;
            }

            if (value instanceof SoapArray) {

                SoapArray array = (SoapArray) value;

                for (Object item : array.values) {

                    if (item == null) {
                        throw new IllegalArgumentException(
                                "Array items cannot be null: " + parameter.getKey());
                    }

                    Element child = document.createElement(array.childTag);
                    child.setTextContent(item.toString());
                    parameterElement.appendChild(child);
                }

            } else {
                parameterElement.setTextContent(value.toString());
            }
        }

        // Serialize XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(
            javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION,
            "yes"
        );

        StringWriter writer = new StringWriter();

        transformer.transform(
                new DOMSource(document),
                new StreamResult(writer));

        return writer.toString();
    }
}