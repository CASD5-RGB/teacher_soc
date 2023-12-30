package com.lu.assess.controller.ex;

/**
 * @author: helu
 * @date: 2022/9/22 11:06
 * @description:
 */
public class NotFinishException extends CommitException{
    public NotFinishException() {
        super();
    }

    public NotFinishException(String message) {
        super(message);
    }

    public NotFinishException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFinishException(Throwable cause) {
        super(cause);
    }

    protected NotFinishException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
