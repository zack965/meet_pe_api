package com.meetpe_api.meetpe_api.Controllers;

import com.meetpe_api.meetpe_api.Services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MailController {
    private EmailService emailService;
    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }
    @GetMapping("/auth/mails/send")
    public String test() throws MessagingException, IOException {
        String recipient = "john.doe@example.com";
        String subject = "Hello to this app";
        String template = "Hello, ${firstName}!\n\n"
                + "This is a message just for you, ${firstName} ${lastName}. "
                + "We hope you're having a great day!\n\n"
                + "Best regards,\n"
                + "The Spring Boot Team";

        Map<String, Object> variables = new HashMap<>();
        variables.put("firstName", "John");
        variables.put("lastName", "Doe");
        //this.emailService.sendEmailAsText(recipient, subject, template,variables);
        //this.emailService.sendHtmlEmail();
        this.emailService.sendEmailFromTemplate();
        return "test";
    }
}
