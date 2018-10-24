package com.alice.nsgogo.enums;

/**
 * @author Aozaki on 2018/3/27 0027.
 * @version 1.0
 * @since 1.0
 */
public enum ResultCode {
    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功!"),
    /**
     * 操作失败
     */
    FAILED(1, "操作失败!"),
    /**
     * 操作失败
     */
    SYSTEM_LOCKED(500, "系统锁定!"),
    /**
     * 验证失败
     */
    OAUTH_FAILED(403, "客户端验证失败，请登录!"),
    /**
     * 操作失败
     */
    SERVER_ERROR(500, "服务器异常!"),
    /**
     * 搜索数据库结果空
     */
    SEARCH_NULL(1010, "未能找到结果！"),

    /**
     * oauth2_验证专用代码
     */
    OAUTH_SUCCESS(200, "验证成功"),

    /**
     * 请求方法不存在
     */
    NOT_FOUND(404, "请求方法不存在"),
    /**
     * 请求方法不允许
     */
    METHOD_NOT_SUPPORT(405, "请求方法不允许"),
    /**
     * 请求出错
     */
    BAD_REQUEST(400, "请求出错。"),
    /**
     * 参数为空
     */
    PARAMETER_NULL(401, "必填参数为空！"),
    /**
     * 参数出错
     */
    PARAMETER_ERROR(401, "参数不合法！"),
    /**
     * 验证失败
     */
    OAUTH_AUTHORIZE_FAILED(401, "客户端验证失败，可能是错误的client_id/client_secret。"),
    /**
     * 验证失败
     */
    OAUTH_ERROR(403, "客户端验证失败，用户名或密码错误！");

    private final int code;

    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
