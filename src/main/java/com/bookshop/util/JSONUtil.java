package com.bookshop.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONUtil {
    public JSONObject success(Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "success");
        jsonObject.put("status", "1");
        jsonObject.put("data", object);
        return jsonObject;
    }

    public JSONObject fail(Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "fail");
        jsonObject.put("status", "0");
        jsonObject.put("data", object);
        return jsonObject;
    }
}