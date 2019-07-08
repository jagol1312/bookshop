package com.bookshop.util;

public class ResponseModel {
    private boolean success;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseModel() {
    }

    public ResponseModel(String msg) {
        this.setSuccess(true);
        this.setMsg(msg);
    }

    public ResponseModel(boolean success, String msg) {
        this.setSuccess(success);
        this.setMsg(msg);
    }
}
