package com.lu.assess.controller.ex;

/**
 * @author: helu
 * @date: 2022/10/29 11:45
 * @description:
 */
public class NullResultException extends CommitException{
    public NullResultException() {
        super();
    }

    public NullResultException(String message) {
        super(message);
    }

    public NullResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullResultException(Throwable cause) {
        super(cause);
    }

    protected NullResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
