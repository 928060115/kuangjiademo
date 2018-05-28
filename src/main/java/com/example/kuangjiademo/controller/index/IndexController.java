package com.example.kuangjiademo.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuyang
 * @since 2018/5/28 15:50
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/cms")
    public String index(){
        return "/cms/index.html";
    }
}
