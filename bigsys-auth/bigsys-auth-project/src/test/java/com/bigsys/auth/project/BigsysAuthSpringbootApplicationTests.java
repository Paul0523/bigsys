package com.bigsys.auth.project;

import com.bigsys.auth.project.db.model.User;
import com.bigsys.auth.project.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BigsysAuthSpringbootApplicationTests {

	@Resource
    private UserService userService;

	@Test
	public void contextLoads() {
	    for (int i = 0; i < 100; i++) {
	        User user = new User();
	        user.setName("jkakdf");
	        user.setPhone("faskdjfa");
	        userService.addOrUpdate(user);
        }
    }

}
