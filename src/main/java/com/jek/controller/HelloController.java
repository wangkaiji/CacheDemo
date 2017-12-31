package com.jek.controller;

import com.jek.enity.User;
import com.jek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/${app.name}")
public class HelloController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "")
    @ResponseBody
    public User get(@RequestParam(required = true) Integer id){
        User user = userService.get(id);
        if(user==null)
            user = new User();
        return user;
    }

    @RequestMapping(value="update")
    @ResponseBody
    public String update(@RequestParam Integer id,@RequestParam String name){
        User user = userService.get(id);
        user.setName(name);
        try {
            userService.update(user);
        }catch (Exception e){
            return "error";
        }
        return "success";
    }


}
