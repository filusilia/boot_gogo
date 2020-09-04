package com.alice.boot.util.http;

import com.alice.boot.common.enums.RequestMethod;
import com.alice.boot.common.exception.MethodNotSupportException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * http request util
 * param @see{@link HttpClientRequest}
 * <p>support request method "GET","POST", "HEAD", "OPTIONS" see{@link RequestMethod}</p>
 *
 * @author alice
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public class HttpClientUtil {

    private static final PoolingHttpClientConnectionManager CONN_MGR;
    private static final RequestConfig REQUEST_CONFIG;
    private static final int MAX_CONNECT_TIMEOUT = 20000;
    private static final int MAX_SOCKET_TIMEOUT = 30000;
    private static final int MAX_CONNECT_REQUEST_TIMEOUT = 50000;
    private static final int MAX_TOTAL = 100;

    static {
        // connecting pools
        CONN_MGR = new PoolingHttpClientConnectionManager();
        //pool size
        CONN_MGR.setMaxTotal(MAX_TOTAL);
        CONN_MGR.setDefaultMaxPerRoute(CONN_MGR.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();

        // timeout unit : milliseconds
        configBuilder.setConnectTimeout(MAX_CONNECT_TIMEOUT);
        configBuilder.setSocketTimeout(MAX_SOCKET_TIMEOUT);
        configBuilder.setConnectionRequestTimeout(MAX_CONNECT_REQUEST_TIMEOUT);

        // check availability
        //configBuilder.setStaleConnectionCheckEnabled(true);
        REQUEST_CONFIG = configBuilder.build();
    }

    /**
     * http request--get
     *
     * @param httpClientRequest @see {@link HttpClientRequest}
     * @return HttpClientResponse @see {@link HttpClientResponse}
     * @throws MethodNotSupportException please make sure request.methods is RequestMethod.GET
     *                                   RequestMethod @see {@link RequestMethod}
     */
    public static HttpClientResponse get(HttpClientRequest httpClientRequest) throws MethodNotSupportException {
        if (Objects.equals(httpClientRequest.getMethod(), RequestMethod.GET)) {
            return doRequest(httpClientRequest);
        } else {
            throw new MethodNotSupportException("HttpClientUtils.get");
        }
    }

    /**
     * http request--post
     *
     * @param httpClientRequest @see {@link HttpClientFormRequest} or @see {@link HttpClientJsonRequest}
     * @return HttpClientResponse @see {@link HttpClientResponse}
     * @throws MethodNotSupportException please make sure request.methods is RequestMethod.POST
     *                                   RequestMethod @see {@link RequestMethod}
     */
    public static HttpClientResponse post(HttpClientRequest httpClientRequest) throws MethodNotSupportException {
        if (Objects.equals(httpClientRequest.getMethod(), RequestMethod.POST)) {
            return doRequest(httpClientRequest);
        } else {
            throw new MethodNotSupportException("HttpClientUtils.post");
        }
    }

    /**
     * http request
     * <p>
     * support request method "GET","POST", "HEAD", "OPTIONS" @see{@link RequestMethod}
     * <p>
     *
     * @param httpClientRequest get:HttpClientRequest @see {@link HttpClientRequest}
     *                          post:HttpClientFormRequest @see {@link HttpClientFormRequest}
     *                          HttpClientJsonRequest @see {@link HttpClientJsonRequest}
     * @return HttpClientResponse @see {@link HttpClientResponse}
     * @throws MethodNotSupportException please make sure request.methods in {@link RequestMethod}
     */
    public static HttpClientResponse doRequest(HttpClientRequest httpClientRequest) {
        try {
            HttpRequestBase baseRequest = httpClientRequest.getHttpRequest();
            Header[] headers = httpClientRequest.getAllHeaders();
            if (null != headers && headers.length > 0) {
                baseRequest.setHeaders(headers);
            }
            baseRequest.setConfig(REQUEST_CONFIG);
            if (httpClientRequest instanceof BaseEntityRequest) { //to post entity
                ((HttpEntityEnclosingRequestBase) baseRequest).setEntity(((BaseEntityRequest) httpClientRequest).getEntity());
            }
            return toXHttpResponse(baseRequest, httpClientRequest.getResponseDefaultCharset(), httpClientRequest.isUseSSL());
        } catch (IOException e) {
            e.printStackTrace();
            return getErrorXResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (MethodNotSupportException e) {
            return getErrorXResponse(HttpStatus.SC_METHOD_NOT_ALLOWED, e.getMessage());
        }
    }

    private static HttpClientResponse getErrorXResponse(int code, String errorMsg) {
        return new HttpClientResponse(code, errorMsg, null, null, null);
    }

    private static HttpClientResponse toXHttpResponse(HttpUriRequest request, String defaultCharset, boolean useSSL) throws IOException {

        try (CloseableHttpClient httpClient = useSSL ? getHttpsClient() : HttpClients.createDefault()) {
            HttpResponse response = httpClient.execute(request);
            HttpClientResponse result = new HttpClientResponse(response.getStatusLine().getStatusCode(), response.getAllHeaders());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //content encoding
                String charset = (null == entity.getContentEncoding()) ? defaultCharset : entity.getContentEncoding().getValue();
                result.setContentEncoding(charset);
                //content type
                String contentType = (null == entity.getContentType()) ? "" : entity.getContentType().getValue();
                result.setContentType(contentType);

                try {
                    result.setResponseText(EntityUtils.toString(entity, defaultCharset));
                } finally {
                    try {
                        EntityUtils.consume(response.getEntity());
                    } catch (IOException e) {
                        log.error("consume error ,error message => {}", e.getMessage());
                    }
                }
            }
            return result;
        }
    }

    private static CloseableHttpClient getHttpsClient() {
        return HttpClients.custom()
                .setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(CONN_MGR)
                .setDefaultRequestConfig(REQUEST_CONFIG)
                .build();
    }

    /**
     * create SSLConnectionSocketFactory to trust all
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
//        try {
//            SSLContext sslcontext = SSLContexts.custom()
//                    .loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true)
//                    .build();
//            return new SSLConnectionSocketFactory(sslcontext,
//                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
//            e.printStackTrace();
//        }
        return null;
    }


}

