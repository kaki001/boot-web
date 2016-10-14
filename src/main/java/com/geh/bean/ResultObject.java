package com.geh.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by HuoBi on 2016/10/10.
 */
public class ResultObject implements Serializable {

    /**
     * Validator处理失败的标志
     */
    public static final String CHACK_ERROR_CODE = "W0001";

    /**
     * 系统错误的标志
     */
    public static final String SYSTEM_ERROR_CODE = "E0001";

    /**
     * 返回码
     */
    private String code = null;

    /**
     * 返回信息
     */
    private String msg = null;

    /**
     * 结果对象
     */
    private Object result = null;

    public ResultObject() {
    }

    public ResultObject(String code, String msg, Object res) {
        this.code = code;
        this.msg = msg;
        this.result = res;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 转换成JSON结果.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
