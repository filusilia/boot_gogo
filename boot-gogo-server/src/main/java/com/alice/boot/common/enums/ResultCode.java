package com.alice.boot.common.enums;

/**
 * @author alice on 2020/09/01
 * <p>
 * rest请求返回枚举类
 * 1x:服务类异常
 * 2x:用户类异常
 * 3x:业务类异常
 * 4x:操作类异常
 * </p>
 * @version 1.0
 * @since 1.0
 */
public enum ResultCode {
    /**
     * 操作成功
     */
    SUCCESS("0000", "操作成功!"),
    /**
     * 操作失败
     */
    FAILED("1001", "操作失败!"),
    /**
     * 操作失败
     */
    SYSTEM_LOCKED("1002", "系统锁定!"),
    /**
     * 验证失败
     */
    OAUTH_FAILED("1003", "客户端验证失败，请登录!"),
    /**
     * 权限不足
     */
    PERMISSION_DENIED("1004", "客户端验证失败，权限不足!"),
    /**
     * 操作失败
     */
    SERVER_ERROR("1005", "服务器异常!"),

    /**
     * 请求方法不存在
     */
    NOT_FOUND("1006", "请求方法不存在"),
    /**
     * 请求方法不允许
     */
    METHOD_NOT_SUPPORT("1007", "请求方法不允许"),
    /**
     * 请求出错
     */
    BAD_REQUEST("1008", "请求出错。"),
    /**
     * 参数为空
     */
    PARAMETER_NULL("1009", "必填参数为空！"),
    /**
     * 参数出错
     */
    PARAMETER_ERROR("1010", "参数不合法！"),
    /**
     * 验证失败
     */
    OAUTH_ERROR("1011", "客户端验证失败，用户名或密码错误！"),

    /**
     * 业务逻辑出错(自定义异常信息内容)
     */
    BUSINESS_ERROR("9998", "操作失败！"),
    /**
     * 非法请求
     */
    DANGER_REQUEST("9999", "危险请求，请勿非法操作！");

    private final String code;

    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
