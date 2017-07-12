package com.zbl.web.response;

/**
 * Json响应工厂
 * 用于移动端
 * <p>
 * Created by James on 2017/6/18.
 */
public class ResponseFactory {

    public static ResponseFactory mInstance;
    //响应代码
    private static final int CODE_SUCCESS = 200;//成功
    private static final int CODE_FAILED = 500;//失败

    public static ResponseFactory getInstance() {
        if (mInstance == null) {
            synchronized (ResponseFactory.class) {
                if (mInstance == null) {
                    mInstance = new ResponseFactory();
                }
            }
        }
        return mInstance;
    }

    public Object getResponse(Object data) {
        GetResponse response = new GetResponse(CODE_SUCCESS);
        response.setResult(data);
        return response;
    }

    public Object getResponse() {
        return new GetResponse(200);
    }

    public Object getFailResponse() {
        return new GetResponse(CODE_FAILED);
    }
}
