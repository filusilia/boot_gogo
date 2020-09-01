package com.alice.boot.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseBusinessException extends RuntimeException {
    private static final long serialVersionUID = -1L;
    /**
     * 结果码
     */
    private String code;

    /**
     * 结果码描述
     */
    private String message;


    public BaseBusinessException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
