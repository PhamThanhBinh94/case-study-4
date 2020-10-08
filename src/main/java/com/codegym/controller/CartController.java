package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.BillDetailService;
import com.codegym.service.BillService;
import com.codegym.service.CustomerService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CartController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private BillService billService;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ModelAndView index(HttpSession session){
        if (session.getAttribute("cart") == null){
            List<Item> cart = new ArrayList<Item>();
            session.setAttribute("cart", cart);
        }
        ModelAndView modelAndView = new ModelAndView("main/checkout");
        modelAndView.addObject("total", sum(session));
        return modelAndView;
    }

    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public String index(@PathVariable("id") String id, HttpSession session){
        if (session.getAttribute("cart") == null){
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(productService.findById(id), 1));
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExist(id, cart);
            System.out.println("index: " + index);
            if (index == -1){
                cart.add(new Item(productService.findById(id),1));
            } else {
                int quantity = cart.get(index).getQuantity() +1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/checkout";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") String id, HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = isExist(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "redirect:/checkout";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, HttpSession session) {
        String[] quantities = request.getParameterValues("quantity");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for (int i = 0 ; i < cart.size();i++){
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart", cart);
        return "redirect:/checkout";
    }


    private int isExist(String id, List<Item> cart){
        for (int i=0; i <cart.size();i++){
            if (cart.get(i).getProduct().getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    private double sum(HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double s = 0;
        for (Item item: cart){
            s += item.getQuantity()
                    * item.getProduct().getPrice();
        }
        return s;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void order(HttpSession session,@RequestBody Customer customer) {
        customerService.save(customer);
        Bill bill = new Bill();
        bill.setCustomer_id(customer.getPhone());
        bill.setAddress(customer.getAddress());
        bill.setStatus("Waiting confirm!");
        billService.save(bill);
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for (Item item : cart) {
            Product product = productService.findById(item.getProduct().getId());
            product.setAmount(product.getAmount() - item.getQuantity());
            productService.save(product);
            BillDetail billDetail = new BillDetail();
            billDetail.setBillId(bill.getBillId());
            billDetail.setUnit_price(item.getProduct().getPrice());
            billDetail.setProductId(item.getProduct().getId());
            billDetail.setAmount(item.getQuantity());
            billDetailService.save(billDetail);
        }
    }
}
