package com.bigsys.auth;

import com.bigsys.dubbo.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * controller
 * @author hwy
 *
 */
@Controller
public class MyController {

    @Resource
    private TestService testService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public String testSay(@RequestParam(value = "name",defaultValue = "") String name){
        StringBuffer sb = new StringBuffer();
        sb.append("Dubbo: ").append("");
        System.out.println(testService.sayHello());
        return testService.sayHello();
    }

}
