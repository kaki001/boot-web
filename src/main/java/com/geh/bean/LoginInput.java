package com.caimao.jyt.ashare.bean;


import com.caimao.jyt.ashare.validator.annotation.NotEmpty;

/**
 * Created by HuoBi on 2016/10/10.
 */
public class LoginInput {

    /**
     * "inputid": "输入账号"
     */
    private String inputid = null;

    /**
     * "trdpwd": "交易密码",
     */
    @NotEmpty(field = "交易密码")
    private String trdpwd = null;

    /**
     * "operway": "客户操作渠道(与验证码渠道相同)",
     */
    private String operway = null;

    /**
     * "verify": "验证码",
     */
    private String verify = null;

    /**
     * "channel": "外围渠道号(与包头外围渠道号一致)",
     */
    private String channel = null;

    /**
     * "IMEI": "IMEI(可选)",
     */
    private String IMEI = null;

    /**
     * "UNIQUEID": "设备唯一号(可选)"
     */
    private String UNIQUEID = null;

    public String getInputid() {
        return inputid;
    }

    public void setInputid(String inputid) {
        this.inputid = inputid;
    }

    public String getTrdpwd() {
        return trdpwd;
    }

    public void setTrdpwd(String trdpwd) {
        this.trdpwd = trdpwd;
    }

    public String getOperway() {
        return operway;
    }

    public void setOperway(String operway) {
        this.operway = operway;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getUNIQUEID() {
        return UNIQUEID;
    }

    public void setUNIQUEID(String UNIQUEID) {
        this.UNIQUEID = UNIQUEID;
    }

    @Override
    public String toString() {
        return "LoginInput{" +
                "inputid='" + inputid + '\'' +
                ", trdpwd='" + trdpwd + '\'' +
                ", operway='" + operway + '\'' +
                ", verify='" + verify + '\'' +
                ", channel='" + channel + '\'' +
                ", IMEI='" + IMEI + '\'' +
                ", UNIQUEID='" + UNIQUEID + '\'' +
                '}';
    }
}
