package com.wxy.bean;

import com.alibaba.druid.support.json.JSONUtils;

/**
 * @author wxy
 * @create 2018-01-15 11:03
 * @desc 返回结果集
 **/
public class DataResult {

    /**
     * 返回状态码   (默认0:成功,1:失败)
     */
    private int code = 0;

    /**
     * 返回的中文消息
     */
    private String message;

    /**
     * 成功时携带的数据
     */
    private Object data;

    public int getCode() {
        return code;
    }

    public DataResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public DataResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public DataResult setData(Object data) {
        this.data = data;
        return this;
    }

    public DataResult addSuccess(String message) {
        this.message = message;
        this.code = 0;
        this.data = null;
        return this;
    }

    public DataResult addError(String message) {
        this.message = message;
        this.code = 1;
        this.data = null;
        return this;
    }

    public DataResult addFail(String message) {
        this.message = message;
        this.code = 999;
        this.data = null;
        return this;
    }

    public DataResult addWarn(String message) {
        this.message = message;
        this.code = 2;
        this.data = null;
        return this;
    }

    public DataResult success(Object data) {
        this.message = "success";
        this.data = data;
        this.code = 0;
        return this;
    }

    public boolean isSuccess() {
        return getCode() == 0;
    }

    @Override
    public String toString() {
//        return JsonKit.toJson(this);
        return JSONUtils.toJSONString(this);
    }

}
