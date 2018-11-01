package com.alice.nsgogo.result;


import com.alice.nsgogo.common.Constants;
import com.alice.nsgogo.enums.ResultCode;

import java.io.Serializable;

/**
 * result bean
 *
 * @author alice
 * resultCode see{@link ResultCode}
 * @since 1.0
 */
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = -7550775412778691222L;
    private final String APIVersion = Constants.VER;
    private int code;
    private String message;
    private T data;

    /**
     * 请求成功
     *
     * @return {
     * data:'',
     * message:'操作成功!',
     * code:'0'
     * }
     */
    public static <P> ResultResponse<P> success() {
        return success(null);
    }

    /**
     * 请求成功(包含data数据)
     *
     * @param data 请求成功返回的数据
     * @return {
     * data:data,
     * message:'操作成功!',
     * code:'0'
     * }
     */
    public static <P> ResultResponse<P> success(P data) {
        ResultResponse<P> result = new ResultResponse<>();
        result.code = ResultCode.SUCCESS.getCode();
        result.message = ResultCode.SUCCESS.getMessage();
        result.data = data;
        return result;
    }

    /**
     * 请求失败
     *
     * @return {
     * message:'操作失败!',
     * message:'1'
     * }
     */
    public static <P> ResultResponse<P> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 请求失败
     *
     * @param code 错误代码
     * @return {
     * message:'操作失败!',
     * code:'1'
     * }
     */
    public static <P> ResultResponse<P> failed(ResultCode code) {
        ResultResponse<P> result = new ResultResponse<>();
        result.code = code.getCode();
        result.message = code.getMessage();
        return result;
    }

    /**
     * 自定义请求返回结果
     *
     * @param msg 自定义错误信息
     * @return {
     * message:msg,
     * code:code
     * }
     */
    public static <P> ResultResponse<P> init(int code, String msg) {
        return init(code, msg, null);
    }

    /**
     * 自定义请求返回结果
     *
     * @param msg 自定义错误信息
     * @return {
     * message:msg,
     * code:code
     * }
     */
    public static <P> ResultResponse<P> init(int code, String msg, P data) {
        ResultResponse<P> result = new ResultResponse<>();
        result.code = code;
        result.message = msg;
        result.data = data;
        return result;
    }

    /**
     * 自定义请求返回结果
     *
     * @param resultCode oauth2代码
     * @return {
     * message:msg,
     * status:status
     * }
     */
    public static <P> ResultResponse<P> oauth2(final ResultCode resultCode, final P data) {
        ResultResponse<P> result = new ResultResponse<>();
        result.code = resultCode.getCode();
        result.message = resultCode.getMessage();
        result.data = data;
        return result;
    }

    /**
     * 转换成string返回
     *
     * @param msg 自定义错误信息
     * @return {
     * message:msg,
     * status:status
     * }
     */
    public static <P> String returnStr(int code, String msg, P data) {
        return "{" +
                "\"code\":\"" + code +
                "\", \"message\":\"" + msg +
                "\", \"data\":\"" + data +
                "\"}";
    }

    public String getAPIVersion() {
        return APIVersion;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
