package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/checkout")
public class CartController {
    public static final String FROM_EMAIL = "langquang1995@gmail.com";
    @Autowired
    private CustomerService customerService;

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private BillService billService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SendEmailService sendEmailService;

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
        bill.setCustomerId(customer.getPhone());
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

        MailRequest mailRequest = new MailRequest();
        mailRequest.setName(customer.getName());
        mailRequest.setTo(customer.getEmail());
        mailRequest.setSubject("Xác nhận đơn hàng #" + bill.getBillId());
        mailRequest.setFrom(FROM_EMAIL);

        String billHTML = "";


        List<BillDetail> billDetails = billDetailService.findAllByBillId(bill.getBillId());
        int total = 0;
        for(BillDetail detail : billDetails){
            Product product = productService.findById(detail.getProductId());
            billHTML += "<tr style=\"border: 1px solid black\">\n" +
                    "            <td><img src='"+ product.getImage() +"'></td>\n" +
                    "            <td>"+ product.getName() +"</td>\n" +
                    "            <td>"+ detail.getAmount() +"</td>\n" +
                    "            <td>"+ detail.getUnit_price() +"</td>\n" +
                    "        </tr>";
            total += detail.getAmount() * detail.getUnit_price();
        }

        Map<String, Object> model = new HashMap<>();
        model.put("customerName", customer.getName());
        model.put("billId", bill.getBillId());
        model.put("customerAddress",customer.getAddress());
        model.put("customerPhone",customer.getPhone());
        model.put("total",total);
        model.put("billDetails", billHTML);

        sendEmailService.sendEmail(mailRequest, model);

    }
}
