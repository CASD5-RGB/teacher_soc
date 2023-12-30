package com.lu.assess.controller.ex;

/**
 * @author: helu
 * @date: 2022/7/29 16:56
 * @description:
 */
public class GradeExcption extends CommitException{
    public GradeExcption() {
        super();
    }

    public GradeExcption(String message) {
        super(message);
    }

    public GradeExcption(String message, Throwable cause) {
        super(message, cause);
    }

    public GradeExcption(Throwable cause) {
        super(cause);
    }

    protected GradeExcption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
