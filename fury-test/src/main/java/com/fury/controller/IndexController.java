package com.fury.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: pxp
 * @Description:
 * @CreateDate: 2018/1/20 18:39
 */
@Controller
public class IndexController {

    @RequestMapping("/freeMarker")
    public String testFreeMarker(ModelMap map) {
        map.addAttribute("name", "test freemarker");
        map.put("sex", 1);
        List<String> userlist = new ArrayList<String>();
        userlist.add("张三");
        userlist.add("李四");
        userlist.add("王五");
        map.put("userlist", userlist);
        return "index";
    }

    @RequestMapping("/jsp")
    public String testJSP() {
        return "index";
    }

}
