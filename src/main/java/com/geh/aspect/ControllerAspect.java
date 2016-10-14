package com.geh.aspect;

import com.geh.bean.ResultObject;
import com.geh.component.MessageComponent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Locale;

@Aspect
@Component
public class ControllerAspect {

    @Autowired
    private MessageComponent messageComponent;

    /**
     * 声明环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(public * com.geh.controller..*.*(..))")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        org.slf4j.Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getMethod().getName();
        Object[] args = pjp.getArgs();
        Object input = args[0];
        logger.info("Start {}, input:{}", methodName, input);
        BindingResult validatorRes = (BindingResult) args[1];

        if (validatorRes.hasErrors()) {
            String message = validatorRes.getFieldError().getDefaultMessage();
            ResultObject result = new ResultObject(ResultObject.CHACK_ERROR_CODE, message, null);
            logger.warn("End {}, req:{}, warn message:{}", methodName, input, message);
            return result;
        }
        long start = System.currentTimeMillis();
        Object rtn = null;
        try {
            rtn = pjp.proceed();
        } catch (Exception ex) {
            ex.printStackTrace();
            long end = System.currentTimeMillis();
            logger.error("End with exception :{}, exe time:{}ms, req:{}, message:{}", methodName, (end - start), input, ex);
            // ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            // HttpServletRequest request = attributes.getRequest();
            // Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
            // Locale locale = LocaleContextHolder.getLocale();
            ResultObject result = new ResultObject(ResultObject.SYSTEM_ERROR_CODE, messageComponent.getMsg("system.error"), null);
            return result;
        }
        long end = System.currentTimeMillis();
        ResultObject result = (ResultObject) rtn;
        logger.debug("End {}, exe time:{}ms, req:{} ---> res:{}", methodName, (end - start), input, result);
        logger.info("End {}, result:{}, exe time:{}ms", methodName, result, (end - start));
        return rtn;
    }

}

