package com.alice.boot.util.http;

import com.alice.boot.common.enums.RequestMethod;
import com.alice.boot.common.exception.MethodNotSupportException;
import org.apache.http.Header;
import org.apache.http.client.methods.*;
import org.apache.http.message.BasicHeader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * HttpClientUtils with request body
 *
 * @author alice
 * @since 1.0
 */
public class HttpClientRequest {
    private final String url;
    private RequestMethod method;
    /**
     * param in query string
     */
    private final Map<String, Object> paramsMap;
    private final Map<String, Header> headerMap;
    /**
     * default charset : utf-8
     */
    private String requestCharset = "UTF-8";
    private String responseDefaultCharset = "UTF-8";
    private boolean useSSL;

    /**
     * @param url    url
     * @param method <p>support request method "GET", "HEAD", "OPTIONS" see{@link RequestMethod}</p>
     * @throws MethodNotSupportException @see param method
     */
    public HttpClientRequest(String url, RequestMethod method) throws MethodNotSupportException {
        this.url = url;
        checkMethod(method);
        this.method = method;
        paramsMap = new LinkedHashMap<>();
        headerMap = new LinkedHashMap<>();
    }

    public HttpClientRequest(String url) throws MethodNotSupportException {
        this(url, RequestMethod.GET);
    }

    //Check request method, "GET", "HEAD", "OPTIONS" is supported
    protected void checkMethod(RequestMethod method) throws MethodNotSupportException {
        if (null == method) {
            throw new MethodNotSupportException(null);
        }
        if (!RequestMethod.GET.equals(method) && !RequestMethod.HEAD.equals(method) && !RequestMethod.OPTIONS.equals(method)) {
            throw new MethodNotSupportException(method.name());
        }
    }

    public String getUrl() {
        return url;
    }

    public Map<String, Object> getParams() {
        return paramsMap;
    }

    public void addParam(String name, Object value) {
        if (null != value) {
            paramsMap.put(name, value);
        }
    }

    public void addParams(Map<String, Object> urlParams) {
        if (null != urlParams && !urlParams.isEmpty()) {
            this.paramsMap.putAll(urlParams);
        }
    }

    public void removeParam(String name) {
        paramsMap.remove(name);
    }

    public void addHeaders(Map<String, String> headers) {
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    public void addHeader(String name, String value) {
        headerMap.put(name, new BasicHeader(name, value));
    }

    public void removeHeader(String name) {
        headerMap.remove(name);
    }

    public Header getHeader(String name) {
        return headerMap.get(name);
    }

    public Header[] getAllHeaders() {
        return headerMap.values().toArray(new Header[headerMap.size()]);
    }

    public String getRequestCharset() {
        return requestCharset;
    }

    public void setRequestCharset(String requestCharset) {
        this.requestCharset = requestCharset;
    }

    public String getResponseDefaultCharset() {
        return responseDefaultCharset;
    }

    public void setResponseDefaultCharset(String responseDefaultCharset) {
        this.responseDefaultCharset = responseDefaultCharset;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) throws MethodNotSupportException {
        checkMethod(method);
        this.method = method;
    }

    public boolean isUseSSL() {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    public String getQueryString() {
        if (paramsMap.isEmpty()) {
            return "";
        }
        StringBuilder queryStr = new StringBuilder();
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            try {
                queryStr.append("&").append(entry.getKey())
                        .append("=")
                        .append(null == entry.getValue() ? "" : URLEncoder.encode(entry.getValue().toString(), requestCharset));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return queryStr.substring(1);
    }

    public String getUrlWithQueryString() {
        String apiUrl = url;
        String queryStr = getQueryString();
        if (null != queryStr & !Objects.equals("", queryStr)) {
            apiUrl = apiUrl + (apiUrl.contains("?") ? "&" : "?") + queryStr;
        }
        return apiUrl;
    }


    public HttpRequestBase getHttpRequest() throws MethodNotSupportException {
        String apiUrl = getUrlWithQueryString();
        switch (method) {
            case GET:
                return new HttpGet(apiUrl);
            case POST:
                return new HttpPost(apiUrl);
            case HEAD:
                return new HttpHead(apiUrl);
            case OPTIONS:
                return new HttpOptions(apiUrl);
            case PUT:
                return new HttpPut(apiUrl);
            case PATCH:
                return new HttpPatch(apiUrl);
            default:
                throw new MethodNotSupportException(method.name());
        }
    }

}
