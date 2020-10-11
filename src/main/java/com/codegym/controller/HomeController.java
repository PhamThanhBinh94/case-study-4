package com.codegym.controller;

import com.codegym.model.Item;
import com.codegym.model.Product;
import com.codegym.model.ProductDetails;
import com.codegym.model.ProductFilter;
import com.codegym.service.ProductDetailsService;
import com.codegym.service.ProductService;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
        modelAndView.addObject("filter",new ProductFilter());
        modelAndView.addObject("products",products);
        return modelAndView;
    }

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

    @RequestMapping(value = "/filter",method = RequestMethod.POST)
    public ModelAndView filter(@ModelAttribute("filter") ProductFilter filter){
        List<Product> productList = productService.filterProduct(filter.getMinPrice(),filter.getMaxPrice(), filter.getBrands());
        System.out.println("Product list:");
        for (Product product : productList){
            System.out.println(product);
        }
        ModelAndView modelAndView = new ModelAndView("main/store");
        modelAndView.addObject("products", productList);
        modelAndView.addObject("filter",new ProductFilter());
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
