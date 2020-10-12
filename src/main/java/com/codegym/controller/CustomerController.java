package com.codegym.controller;

import com.codegym.model.Bill;
import com.codegym.model.Customer;
import com.codegym.service.BillService;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    private BillService billService;

    @GetMapping("")
    public ModelAndView index(@PageableDefault(size = 5) Pageable pageable){
        Page<Customer> customers = customerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping(value = "/create")
    public ModelAndView saveCustomer(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/customer/create");
            return modelAndView;
        }
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", " New customer created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{phone}")
    public ModelAndView showEditForm(@PathVariable String phone){
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customer", customerService.findById(phone));
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateCustomer(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("customer/edit");
            return modelAndView;
        }
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer updated successfully!");
        return modelAndView;
    }

    @GetMapping("/delete/{phone}")
    public String deleteCustomer(@PathVariable String phone,  RedirectAttributes redirect){
        customerService.delete(phone);
        redirect.addFlashAttribute("success", "Deleted customer successfully!");
        return "redirect:/customers";
    }

    @GetMapping("/search")
    public ModelAndView listCustomers(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Customer> customers;
        if(s.isPresent()){
            customers = customerService.findByNameContaining(s.get(), pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/history/{phone}")
    public ModelAndView viewHistory(@PathVariable String phone, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("customer/history");
        Page<Bill> bills = billService.findAllByCustomerIdOrderByDateDesc(phone, pageable);
        Customer customer = customerService.findById(phone);
        modelAndView.addObject("bills",bills);
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }
}
