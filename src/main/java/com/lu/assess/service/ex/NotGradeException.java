package com.lu.assess.service.ex;

/**
 * @author: helu
 * @date: 2022/7/19 20:49
 * @description:
 */
public class NotGradeException extends ServiceException{
    public NotGradeException() {
        super();
    }

    public NotGradeException(String message) {
        super(message);
    }

    public NotGradeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotGradeException(Throwable cause) {
        super(cause);
    }

    protected NotGradeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
