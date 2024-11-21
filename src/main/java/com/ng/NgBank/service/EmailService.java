package com.ng.NgBank.service;

import com.ng.NgBank.models.ReceipientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Value("${spring.mail.sender}")
    private String senderemail ;

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendMessage(ReceipientDetails receiverDetails) {
        System.out.println("senderemail " + senderemail);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(senderemail);
        simpleMailMessage.setTo(receiverDetails.getReceipentmail());
        simpleMailMessage.setText(receiverDetails.getBody());
        simpleMailMessage.setSubject(receiverDetails.getSubject());
        javaMailSender.send(simpleMailMessage);
        System.out.println("Mail has been successfullsent");
    }

    public static UserDetails getUserDetailsFromSecurityContext() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
