package com.lu.assess.service.ex;

/**
 * @author: helu
 * @date: 2022/9/21 23:55
 * @description:
 */
public class NotDataException extends ServiceException{
    public NotDataException() {
        super();
    }

    public NotDataException(String message) {
        super(message);
    }

    public NotDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotDataException(Throwable cause) {
        super(cause);
    }

    protected NotDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
