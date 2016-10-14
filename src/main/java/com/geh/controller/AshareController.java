package com.geh.controller;

import com.geh.bean.LoginInput;
import com.geh.bean.ResultObject;
import com.geh.reactor.base.TaskEventReactor;
import com.geh.reactor.event.TestEvent;
import com.geh.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/api/")
public class AshareController {

    @Autowired
    private TaskEventReactor taskEventReactor;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value = Constants.CACHE_NAME, key="'TEST001_'+#input.inputid")
    public ResultObject login(@Valid LoginInput input, BindingResult result) {
        ResultObject rst = new ResultObject();
        rst.setCode("1002");
        int i = 0;
        // long t = 18 / i;
        System.out.println("***************************************");

        taskEventReactor.addEvent(new TestEvent(1));

//        redisTemplate.opsForHash().put("test", "kaki", "王峰");
//        redisTemplate.opsForZSet().add("test", "tst", 1.90);
//        System.out.println(redisTemplate.opsForHash().get("test", "kaki"));
        return rst;
    }

}
