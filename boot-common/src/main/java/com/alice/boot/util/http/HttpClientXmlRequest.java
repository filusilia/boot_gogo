package com.alice.boot.util.http;

import com.alice.boot.common.enums.RequestMethod;
import com.alice.boot.common.exception.MethodNotSupportException;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

/**
 * Request for Xml
 *
 * @author Alice
 */
public class HttpClientXmlRequest extends BaseEntityRequest {

    private String xmlString;

    private static final String XML_CONTENT_TYPE = "text/xml;";

    public HttpClientXmlRequest(String url, RequestMethod method) throws MethodNotSupportException {
        super(url, method);
    }

    public HttpClientXmlRequest(String url) throws MethodNotSupportException {
        super(url, RequestMethod.POST);
    }

    public Object getXmlString() {
        return xmlString;
    }

    public void setXmlString(String xmlString) {
        this.xmlString = xmlString;
    }

    @Override
    public HttpEntity getEntity() {
        StringEntity stringEntity = new StringEntity(xmlString, getRequestCharset());
        stringEntity.setContentEncoding(getResponseDefaultCharset());
        stringEntity.setContentType(XML_CONTENT_TYPE);
        return stringEntity;
    }
}
