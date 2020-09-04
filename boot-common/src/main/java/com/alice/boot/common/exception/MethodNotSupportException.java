package com.alice.boot.common.exception;

/**
 * @author alice
 * <p>
 * user-defined Exception;
 * @version 1.0
 * @since 1.0
 */
public class MethodNotSupportException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final int CODE = 1999;

    public MethodNotSupportException(String methodName) {
        super("Method " + methodName + " not support!");
    }

    public static int getCode() {
        return CODE;
    }
}
