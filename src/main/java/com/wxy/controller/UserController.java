package com.wxy.controller;

import com.wxy.model.User;
import com.wxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * @author wxy
 * @create 2018-01-11 09:56
 * @desc ${DESCRIPTION}
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String toindex(HashMap<String,Object> map){
        map.put("users",userService.findAll(1,10));
        return "/adduser";
    }
    @ModelAttribute
    User setUser () {
        return new User ();
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute(value="user") User user, BindingResult result, HashMap<String, Object> map){
        if(result.hasErrors()){
            for (ObjectError objectError:result.getAllErrors()) {
                System.out.println("ERROR:"+objectError.toString());
            }
            map.put("MSG", "出错啦！");
        }else{
            int resultInt =  userService.insertOne(user);
            map.put("MSG", "提交成功！" + resultInt);
        }
        map.put("users",userService.findAll(1,35));
        return "/adduser";
    }

}
