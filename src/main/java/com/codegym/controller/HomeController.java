package com.codegym.controller;

import com.codegym.model.Item;
import com.codegym.model.Product;
import com.codegym.model.ProductDetails;
import com.codegym.service.ProductDetailsService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailsService detailsService;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("main/index");
        List<Product> listTV = productService.findFirst6ByType("tivi");
        List<Product> listTL = productService.findFirst6ByType("tu-lanh");
        List<Product> listDH = productService.findFirst6ByType("dieu-hoa-nhiet-do");
        List<Product> listMG = productService.findFirst6ByType("may-giat");
        modelAndView.addObject("listTV",listTV);
        modelAndView.addObject("listTL",listTL);
        modelAndView.addObject("listDH",listDH);
        modelAndView.addObject("listMG",listMG);
        return modelAndView;
    }

    @GetMapping("/{type}")
    public ModelAndView store(@PathVariable String type, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/main/store");
        Page<Product> products = productService.findAllByType(type,pageable);
        modelAndView.addObject("products",products);
        return modelAndView;
    }

//    @GetMapping("/checkout")
//    public String checkout(){
//        return "main/checkout";
//    }

    @GetMapping("/chi-tiet/{id}")
    public ModelAndView product(@PathVariable("id") String id){
        ModelAndView modelAndView;
        Product product = productService.findById(id);
        if(product != null) {
            modelAndView = new ModelAndView("main/product");
            modelAndView.addObject("product",product);
            ProductDetails details = detailsService.findDetailById(id);
            modelAndView.addObject("details",details);
        } else {
            modelAndView = new ModelAndView("main/blank");
        }
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView listCustomers(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Product> products;
        if(s.isPresent()){
            products = productService.findByNameContaining(s.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/main/blank");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
