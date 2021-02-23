package com.example.ReportPlayer.services.email;


import com.example.ReportPlayer.utils.pojo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@Primary
public class TextEmailSenderServiceImpl implements EmailSenderService{

    @Autowired
    private JavaMailSender javaMailSender;

    private final SimpleMailMessage message = new SimpleMailMessage();

    @Override
    @Async
    public void send(Email email) {
        try {
            System.out.println("********************* SLEEPING NOW FOR TESTING PURPOSE **********************");
            Thread.sleep(10000);
            message.setTo(email.getTo());
            message.setSubject(email.getSubject());
            message.setText(email.getContent());
            message.setFrom(email.getFrom());
            javaMailSender.send(message);
        } catch (MailException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



}
