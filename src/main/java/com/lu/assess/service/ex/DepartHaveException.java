package com.lu.assess.service.ex;

/**
 * @author: helu
 * @date: 2022/7/18 15:43
 * @description:
 */
public class DepartHaveException extends ServiceException{
    public DepartHaveException() {
        super();
    }

    public DepartHaveException(String message) {
        super(message);
    }

    public DepartHaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartHaveException(Throwable cause) {
        super(cause);
    }

    protected DepartHaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
