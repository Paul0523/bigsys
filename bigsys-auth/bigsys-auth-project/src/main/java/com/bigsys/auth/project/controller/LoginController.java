package com.bigsys.auth.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping(value = "/loginRes")
    public boolean loginRes () {
        return true;
    }
}
