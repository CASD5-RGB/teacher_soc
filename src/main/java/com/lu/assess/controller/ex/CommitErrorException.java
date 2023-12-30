package com.lu.assess.controller.ex;

/**
 * @author: helu
 * @date: 2022/12/30 20:45
 * @description:
 */
public class CommitErrorException extends CommitException{
    public CommitErrorException() {
        super();
    }

    public CommitErrorException(String message) {
        super(message);
    }

    public CommitErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommitErrorException(Throwable cause) {
        super(cause);
    }

    protected CommitErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
