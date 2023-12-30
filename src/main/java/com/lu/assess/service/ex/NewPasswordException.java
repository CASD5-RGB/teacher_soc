package com.lu.assess.service.ex;

/**
 * @author: helu
 * @date: 2022/8/1 10:12
 * @description:
 */
public class NewPasswordException extends ServiceException{
    public NewPasswordException() {
        super();
    }

    public NewPasswordException(String message) {
        super(message);
    }

    public NewPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewPasswordException(Throwable cause) {
        super(cause);
    }

    protected NewPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
