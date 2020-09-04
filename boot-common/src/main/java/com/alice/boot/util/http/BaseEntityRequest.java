package com.alice.boot.util.http;

import com.alice.boot.common.enums.RequestMethod;
import com.alice.boot.common.exception.MethodNotSupportException;
import org.apache.http.HttpEntity;

/**
 * @author alice
 * <p>
 * basic request for post
 * </p>
 */
public abstract class BaseEntityRequest extends HttpClientRequest {

    public BaseEntityRequest(String url, RequestMethod method) throws MethodNotSupportException {
        super(url, method);
    }

    public BaseEntityRequest(String url) throws MethodNotSupportException {
        super(url);
    }

    /**
     * Check request method, "POST", "PUT", "PATCH" is supported
     */
    @Override
    protected void checkMethod(RequestMethod method) throws MethodNotSupportException {
        if (null == method) {
            throw new MethodNotSupportException(null);
        }
        if (!RequestMethod.POST.equals(method) && !RequestMethod.PUT.equals(method) && !RequestMethod.PATCH.equals(method)) {
            throw new MethodNotSupportException(method.name());
        }
    }

    /**
     * 基础httpEntity
     *
     * @return HttpEntity
     */
    public abstract HttpEntity getEntity();
}
