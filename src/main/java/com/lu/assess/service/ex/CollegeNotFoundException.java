package com.lu.assess.service.ex;

/**
 * @author: helu
 * @date: 2022/7/17 18:15
 * @description:
 */
public class CollegeNotFoundException extends ServiceException {
    public CollegeNotFoundException() {
        super();
    }

    public CollegeNotFoundException(String message) {
        super(message);
    }

    public CollegeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollegeNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CollegeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
