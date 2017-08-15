package com.paul.bigsys.webdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fangzhipeng on 2017/8/15.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }

}
