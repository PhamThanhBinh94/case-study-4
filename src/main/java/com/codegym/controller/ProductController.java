package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductDetails;
import com.codegym.service.ProductDetailsService;
import com.codegym.service.ProductService;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailsService detailsService;

    @GetMapping("")
    public ModelAndView listProducts(@RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Product> products;
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
    public ModelAndView saveProduct(@ModelAttribute("product") Product product){
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
    public ModelAndView updateProduct(@ModelAttribute("product") Product product) throws IOException {
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

    @GetMapping("/view/{id}")
    public ModelAndView viewProduct(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("product/view");
        Product product = productService.findById(id);
        ProductDetails details = detailsService.findDetailById(id);
        modelAndView.addObject("product",product);
        modelAndView.addObject("details",details);
        return modelAndView;
    }
}
