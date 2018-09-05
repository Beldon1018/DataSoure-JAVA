package com.api.open.util;

import com.api.open.model.ResultModel;
import com.google.gson.Gson;

/**
 * Created by 003 on 2018/9/5.
 */
public class JsonUtil {
    private static Gson mGson = new Gson();

    public static String toSuccess(Object data) {
        return toJson(200, "成功!", data);
    }

    public static String toFail(Object data) {
        return toJson(400, "失败!", data);
    }

    public static String toFail(int code, String message) {
        return toJson(code, message, null);
    }

    public static String toFail(String message, Object data) {
        return toJson(400, message, data);
    }

    public static String toJson(int code, String message, Object data) {
        ResultModel model = new ResultModel();
        model.setCode(code);
        model.setMessage(message);
        model.setResult(data);
        return mGson.toJson(model);
    }
}
