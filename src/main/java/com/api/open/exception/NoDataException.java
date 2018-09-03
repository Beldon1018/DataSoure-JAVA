package com.api.open.exception;

/**
 * 自定义异常-无数据异常
 * Created by 003 on 2018/9/3.
 */
public class NoDataException extends RuntimeException {
    private int code;
    private String msg;

    public NoDataException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
