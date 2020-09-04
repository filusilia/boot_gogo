package com.alice.boot.util.http;

import com.alice.boot.common.enums.RequestMethod;
import com.alice.boot.common.exception.MethodNotSupportException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Request for UrlEncodedForm
 * @author Alice
 */
public class HttpClientFormRequest extends BaseEntityRequest {

    private Map<String, Object> params;

    public HttpClientFormRequest(String url, RequestMethod method) throws MethodNotSupportException {
        super(url, method);
        params = new LinkedHashMap<>();
    }

    public HttpClientFormRequest(String url) throws MethodNotSupportException {
        super(url, RequestMethod.POST);
        params = new LinkedHashMap<>();
    }

    @Override
    public HttpEntity getEntity() {
        List<NameValuePair> pairList = new ArrayList<>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                    .getValue().toString());
            pairList.add(pair);
        }
        return new UrlEncodedFormEntity(pairList, Charset.forName(getRequestCharset()));
    }

    @Override
    public void addParam(String name, Object value) {
        if (null!=value){
            params.put(name, value);
        }
    }

    @Override
    public void addParams(Map<String, Object> params) {
        if (null != params && !params.isEmpty()) {
            this.params.putAll(params);
        }
    }

    @Override
    public void removeParam(String name) {
        params.remove(name);
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }
}
