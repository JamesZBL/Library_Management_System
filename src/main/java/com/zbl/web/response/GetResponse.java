package com.zbl.web.response;

/**
 * Created by James on 2017/6/18.
 */
public class GetResponse {
    private int code;
    private Object result;

    public GetResponse(int code) {
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}