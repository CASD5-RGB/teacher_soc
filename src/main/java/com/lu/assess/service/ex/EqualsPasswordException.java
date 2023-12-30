package com.lu.assess.service.ex;

/**
 * @author: helu
 * @date: 2022/8/1 10:24
 * @description:
 */
public class EqualsPasswordException extends ServiceException{
    public EqualsPasswordException() {
        super();
    }

    public EqualsPasswordException(String message) {
        super(message);
    }

    public EqualsPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public EqualsPasswordException(Throwable cause) {
        super(cause);
    }

    protected EqualsPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
