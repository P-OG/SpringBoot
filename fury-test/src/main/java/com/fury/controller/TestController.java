package com.fury.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pxp
 * @Description: @RestController 标注该类的所有请求方法为 返回json格式 不做视图解析。
 * @CreateDate: 2018/1/19 9:40
 */
@RequestMapping("/testSpringBoot")
@RestController //声明为一个Restful的Controller
public class TestController {

    @Value("${home.province}")
    private String province;

    @RequestMapping(value = "/test1", method = {RequestMethod.GET, RequestMethod.POST})
    public String test1() {
        return "Hello World!" + province;
    }
}
