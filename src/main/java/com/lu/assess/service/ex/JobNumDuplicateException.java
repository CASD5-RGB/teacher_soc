package com.lu.assess.service.ex;

/**
 * @author: helu
 * @date: 2022/7/14 17:28
 * @description:
 */
public class JobNumDuplicateException extends ServiceException{
    public JobNumDuplicateException() {
        super();
    }

    public JobNumDuplicateException(String message) {
        super(message);
    }

    public JobNumDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobNumDuplicateException(Throwable cause) {
        super(cause);
    }

    protected JobNumDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
