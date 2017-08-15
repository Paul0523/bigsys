package com.bigsys.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * controller
 * @author hwy
 *
 */
@Controller
public class MyController {



    @RequestMapping(value = "/test")
    @ResponseBody
    public String testSay(@RequestParam(value = "name",defaultValue = "") String name){
        StringBuffer sb = new StringBuffer();
        sb.append("Dubbo: ").append("");
        return sb.toString();
    }

}
