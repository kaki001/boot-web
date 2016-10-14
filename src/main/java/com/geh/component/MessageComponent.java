package com.geh.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 系统启动后处理消息资源的处理类
 */
@Component
public class MessageComponent {

    @Autowired
    private MessageSource messageSource;

    /**
     * getMsg:取得一条消息. <br/>
     *
     * @param code
     * @param params
     * @return
     */
    public String getMsg(String code, String... params) {
        Locale locale = LocaleContextHolder.getLocale();
        String rtnMsg = "";
        try {
            rtnMsg = messageSource.getMessage(code, params, locale);
        } catch (Exception ex) {
            rtnMsg = code;
        }
        return rtnMsg;
    }

    /**
     * getMsg:取得一条消息. <br/>
     *
     * @param code
     * @return
     */
    public String getMsg(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        String rtnMsg = "";
        try {
            rtnMsg = messageSource.getMessage(code, null, locale);
        } catch (Exception ex) {
            rtnMsg = code;
        }
        return rtnMsg;
    }
}
