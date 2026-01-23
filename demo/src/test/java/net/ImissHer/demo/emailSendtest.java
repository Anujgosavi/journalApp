package net.ImissHer.demo;

import net.ImissHer.demo.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class emailSendtest {

    @Autowired
    private EmailService emailService ;


    @Test
    public void send(){

        emailService.sendEmail("anuj.gosavi23@vit.edu" , "Testing JavaEmailService" , "Hiii, How Are You?");
    }
}
