package com.bigsys.auth.project.controller;

import com.bigsys.auth.project.model.Menu;
import com.bigsys.auth.project.util.response.BSResponse;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    String menu = "[{\"name\":\"aa\",\"hasSub\":true,\"menus\":[{\"name\":\"bb\",\"link\":\"/index\",\"hasSub\":false}]},{\"name\":\"aa\",\"link\":\"/\",\"hasSub\":false},{\"name\":\"aa\",\"link\":\"/\",\"hasSub\":false},{\"name\":\"系统管理\",\"link\":\"/\",\"hasSub\":true,\"menus\":[{\"name\":\"角色管理\",\"link\":\"/role/index\",\"hasSub\":false},{\"name\":\"权限管理\",\"link\":\"/auth/index\",\"hasSub\":false},{\"name\":\"用户管理\",\"link\":\"/user/index\",\"hasSub\":false},{\"name\":\"菜单管理\",\"link\":\"/user/index\",\"hasSub\":false}]}]";

    @RequestMapping(value = "/getMenus")
    public BSResponse getMenus(String userId) {
        JavaType javaType = new ObjectMapper().getTypeFactory().constructParametricType(ArrayList.class, Menu.class);
        List<Menu> menus = null;
        try {
            menus = new ObjectMapper().readValue(menu, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BSResponse.ok(menus);
    }

}
