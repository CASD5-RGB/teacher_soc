package com.lu.assess.service.ex;

/**
 * @author: helu
 * @date: 2022/9/22 10:40
 * @description:
 */
public class NotFinishAssessException extends ServiceException{
    public NotFinishAssessException() {
        super();
    }

    public NotFinishAssessException(String message) {
        super(message);
    }

    public NotFinishAssessException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFinishAssessException(Throwable cause) {
        super(cause);
    }

    protected NotFinishAssessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
