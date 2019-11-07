package com.pactera.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author simonMeng
 * @version 1.0
 * @date 2019/10/13
 **/
@Controller
public class SwaggerCtl {
    @Value("${camel.request.url}")
    public  String keyStr;
    @RequestMapping("/")
    public String index(){
        return "redirect:/swagger-ui.html";
    }
    @RequestMapping("/swagger-ui.html")
    public String swaggerIndex(){
        System.out.println("keyStr:"+keyStr);
        return "index";
    }
}
