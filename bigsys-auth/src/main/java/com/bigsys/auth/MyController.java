package com.bigsys.auth;

import com.bigsys.auth.db.dao.BsTestMapper;
import com.bigsys.auth.db.model.BsTest;
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
    @Resource
    BsTestMapper bsTestMapper;

    @RequestMapping(value = "/test")
    @ResponseBody
    public BsTest testSay(@RequestParam(value = "name",defaultValue = "") String name){
        StringBuffer sb = new StringBuffer();
        sb.append("Dubbo: ").append("");
        System.out.println(testService.sayHello());
        BsTest bsTest = new BsTest();
        bsTest.setName("呜呜呜呜无无");
//        bsTestMapper.insert(bsTest);
        System.out.println(bsTestMapper.selectByPrimaryKey(1));
        return bsTestMapper.selectByPrimaryKey(1);
    }

}
