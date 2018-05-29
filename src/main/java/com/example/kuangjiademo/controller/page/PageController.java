package com.example.kuangjiademo.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuyang
 * @since 2018/5/28 15:50
 */
@Controller
public class PageController {
    @RequestMapping(value = "/")
    public String index(){
        return "/index.html";
    }
   /* @RequestMapping(value = "/login")
    public String login(){
        return "/login.html";
    }*/

}
