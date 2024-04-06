package com.meetpe_api.meetpe_api.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailAsText(String to, String subject, String template,Map<String, Object> variables) {
        String processedTemplate = applyVariables(template, variables);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(processedTemplate);

        javaMailSender.send(message);
    }

    private String applyVariables(String template, Map<String, Object> variables) {
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            String placeholder = "${" + entry.getKey() + "}";
            Object value = entry.getValue();
            template = template.replace(placeholder, value.toString());
        }
        return template;
    }
    public void sendHtmlEmail() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(new InternetAddress("sender@example.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
        message.setSubject("Test email from Spring");

        String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
                "<p>It can contain <strong>HTML</strong> content.</p>";
        message.setContent(htmlContent, "text/html; charset=utf-8");

        javaMailSender.send(message);
    }
    public void sendEmailFromTemplate() throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(new InternetAddress("sender@example.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
        message.setSubject("Test email from my Springapplication");

        // Read the HTML template into a String variable
        String htmlTemplate = readHtmlTemplate("template.html");

        // Replace placeholders in the HTML template with dynamic values
        htmlTemplate = htmlTemplate.replace("${name}", "John Doe");
        htmlTemplate = htmlTemplate.replace("${message}", "Hello, this is a test email.");

        // Set the email's content to be the HTML template
        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        javaMailSender.send(message);
    }
    private String readHtmlTemplate(String filePath) throws IOException {
        // Load the HTML template file from the classpath
        ClassPathResource resource = new ClassPathResource("templates/"+filePath);
        byte[] encoded = Files.readAllBytes(Paths.get(resource.getURI()));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
