package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ModelAndView listProducts(Optional<String> s, Pageable pageable) {
        Page<Product> products;
        System.out.println("s: " + s);
        System.out.println(s.isPresent());
        if (s.isPresent()){
            products = productService.findAllByTypeOrIdOrOrBrand(s.get(),s.get(),s.get(),pageable);
        }else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        modelAndView.addObject("keyword",s.orElse(null));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateProduct(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("products", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(@Validated @ModelAttribute("products") Product product, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        return modelAndView;
        }
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("products", new Product());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProduct(@PathVariable String id){
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("products",product);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateProduct(@Validated @ModelAttribute("products") Product product,BindingResult bindingResult) throws IOException {
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("product/edit");
            return modelAndView;
        }
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("products", new Product());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable String id){
        productService.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        return modelAndView;
    }
}
