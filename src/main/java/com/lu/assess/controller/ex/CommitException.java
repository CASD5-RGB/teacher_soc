package com.lu.assess.controller.ex;

/**
 * @author: helu
 * @date: 2022/7/29 11:47
 * @description:
 */
public class CommitException extends RuntimeException {
    public CommitException() {
        super();
    }

    public CommitException(String message) {
        super(message);
    }

    public CommitException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommitException(Throwable cause) {
        super(cause);
    }

    protected CommitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
