package com.lu.assess.controller.ex;

/**
 * @author: helu
 * @date: 2022/7/29 11:48
 * @description:
 */
public class FinishException extends CommitException{
    public FinishException() {
        super();
    }

    public FinishException(String message) {
        super(message);
    }

    public FinishException(String message, Throwable cause) {
        super(message, cause);
    }

    public FinishException(Throwable cause) {
        super(cause);
    }

    protected FinishException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
