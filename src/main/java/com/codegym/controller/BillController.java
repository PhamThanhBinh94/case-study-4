package com.codegym.controller;

import com.codegym.model.Bill;
import com.codegym.model.BillDetail;
import com.codegym.service.BillDetailService;
import com.codegym.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private BillDetailService billDetailService;

    @GetMapping("")
    public ModelAndView listBills(Pageable pageable){
        Page<Bill> bills = billService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/bill/list");
        modelAndView.addObject("bills", bills);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateBill(){
        ModelAndView modelAndView = new ModelAndView("/bill/create");
        modelAndView.addObject("bills", new Bill());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveBill(@ModelAttribute("bill") Bill bill){
        billService.save(bill);
        ModelAndView modelAndView = new ModelAndView("/bill/create");
        modelAndView.addObject("bills", new Bill());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditBill(@PathVariable Long id){
        Bill bill = billService.editById(id);
        ModelAndView modelAndView = new ModelAndView("/bill/edit");
        modelAndView.addObject("bills",bill);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateBill(@ModelAttribute("bill") Bill bill) throws IOException {
        billService.save(bill);
        ModelAndView modelAndView = new ModelAndView("/bill/edit");
        modelAndView.addObject("bills", new Bill());
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewBill(Pageable pageable, @PathVariable Long id){
        Page<BillDetail> billDetails = billDetailService.findAllByBillId(id, pageable);
        for(BillDetail detail : billDetails){
            System.out.println(detail);
        }
        Bill bill = billService.findByBillId(id);
        ModelAndView modelAndView = new ModelAndView("/bill/view");
        modelAndView.addObject("bill", bill);
        modelAndView.addObject("billDetails", billDetails);
        return modelAndView;
    }


}
