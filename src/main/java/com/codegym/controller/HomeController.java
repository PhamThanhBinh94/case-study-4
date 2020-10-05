package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "main/index";
    }

    @GetMapping("/product")
    public String product(){
        return "main/product";
    }

    @GetMapping("/store")
    public String store(){
        return "main/store";
    }

    @GetMapping("/checkout")
    public String checkout(){
        return "main/checkout";
    }
}
