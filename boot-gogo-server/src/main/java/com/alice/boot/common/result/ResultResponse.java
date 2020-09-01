package com.alice.boot.common.result;


import com.alice.boot.common.Constants;
import com.alice.boot.common.enums.ResultCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * result bean
 *
 * @author alice
 * resultCode see{@link ResultCode}
 * @since 1.0
 */
@ToString
@Getter
public class ResultResponse<T> implements Serializable {
    private static final long serialVersionUID = -6592548838615372080L;
    private final String APIVersion = Constants.VERSION;
    private String code;
    private String message;
    private T data;

    /**
     * 请求成功
     *
     * @return {
     * data:'',
     * message:'操作成功!',
     * code:'0000'
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
     * code:'0000'
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
     * message:'1001'
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
     * code:'1001'
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
    public static <P> ResultResponse<P> init(String code, String msg) {
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
    public static <P> ResultResponse<P> init(String code, String msg, P data) {
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
}
