package com.jufan.util;

import java.util.Map;

/**
 * @Author pengyd
 * @Date 2018/7/12 10:08
 * @function: 封装返回数据
 */
public class ReturnData {
    private String code;

    private String msg;

    private Map<String, Object> data;

    public ReturnData() {
    }

    public ReturnData(String code, String msg, Map<String, Object> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}