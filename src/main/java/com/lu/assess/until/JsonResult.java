package com.lu.assess.until;

import java.io.Serializable;

/**
 * @author: helu
 * @date: 2022/7/14 17:48
 * @description:
 */
public class JsonResult<E> implements Serializable{
    private Integer status;
    private String message;
    private E data;

    public JsonResult() {
    }

    //获取异常时用
    public JsonResult(Throwable e){
        //获取异常信息
        this.message = e.getMessage();
    }

    public JsonResult(Integer status) {
        this.status = status;
    }

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(E data) {
        this.data = data;
    }

    public JsonResult(Integer status, String message, E data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public JsonResult(Integer status, E data) {
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
