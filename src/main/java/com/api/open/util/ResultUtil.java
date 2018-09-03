package com.api.open.util;

import com.api.open.model.ResultModel;

/**
 * Created by 003 on 2018/9/3.
 */
public class ResultUtil {

    public static ResultModel toSuccess(Object data) {
        return toObject(200, "成功!", data);
    }

    public static ResultModel toFail(Object data) {
        return toObject(400, "失败!", data);
    }

    public static ResultModel toFail(int code, String message) {
        return toObject(code, message, null);
    }

    public static ResultModel toFail(String message, Object data) {
        return toObject(400, message, data);
    }

    public static ResultModel toObject(int code, String message, Object data) {
        ResultModel model = new ResultModel();
        model.setCode(code);
        model.setMessage(message);
        model.setResult(data);
        return model;
    }

}
