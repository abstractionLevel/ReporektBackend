package com.example.ReportPlayer.controller.services.email;

import com.example.ReportPlayer.controller.utility.SmtpServerRule;
import com.example.ReportPlayer.utils.pojo.Email;
import com.example.ReportPlayer.services.email.TextEmailSenderServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import javax.mail.internet.MimeMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailSenderServiceIT {

    @Autowired
    private TextEmailSenderServiceImpl textEmailSenderServiceImpl;

    @Test
    public void shouldSendSingleMail() throws Exception {
        Email mail = new Email();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo("deluciaugo@gmail.com");
        mail.setSubject("Spring Mail Integration Testing with JUnit and GreenMail Example");
        mail.setContent("We show how to write Integration Tests using Spring and GreenMail.");

        textEmailSenderServiceImpl.send(mail);


    }

}
