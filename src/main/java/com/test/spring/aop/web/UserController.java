package com.test.spring.aop.web;

import com.test.spring.aop.domain.entiry.User;
import com.test.spring.aop.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by miaorf on 2016/7/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/all")
    public List<User> users(){
        try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        List<User> all = userService.getAll();
        return all;
    }
}
