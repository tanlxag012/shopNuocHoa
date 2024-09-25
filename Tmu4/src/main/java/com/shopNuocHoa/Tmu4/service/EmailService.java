package com.shopNuocHoa.Tmu4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("shopnuochoataolao@gmail.com");
        message.setSubject("Tiền về");
        message.setText("Shop vừa có 1 đơn đặt hàng");
        message.setFrom("duongmuoibotngot147@gmail.com");
        mailSender.send(message);
    }

}
