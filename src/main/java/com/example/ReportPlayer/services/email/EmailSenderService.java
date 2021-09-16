package com.example.ReportPlayer.services.email;

import com.example.ReportPlayer.utils.pojo.Email;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailSenderService {

    void send(Email email , String template)throws MessagingException, IOException;

}
