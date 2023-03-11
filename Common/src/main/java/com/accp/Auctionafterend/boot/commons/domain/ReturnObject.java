package com.accp.Auctionafterend.boot.commons.domain;

public class ReturnObject<T> {
    private String code;//处理成功获取失败的标记：1---成功,0---失败
    private String message;//提示信息
    private T retData;//其它数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRetData() {
        return retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }
}
