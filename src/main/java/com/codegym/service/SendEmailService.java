package com.codegym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.security.auth.login.Configuration;

@Service
public class SendEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

//    @Autowired
//    private Configuration config;

    public void sendEmail(String to, String body, String topic) {
        System.out.println("Sending email.....");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("langquang1995@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
        System.out.println("Sent");
    }
}
