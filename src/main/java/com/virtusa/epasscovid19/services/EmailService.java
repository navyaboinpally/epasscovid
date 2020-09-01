package com.virtusa.epasscovid19.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender sender;

    @Autowired
   private OtpService otpService;


    public void generateOtp() throws MessagingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        int otp = otpService.generateOTP(username);

        Map<String, Object> replacements = new HashMap<>();
        replacements.put("user", username);
        replacements.put("otpnum", String.valueOf(otp));

       sendOtpMessage(username, "OTP -SpringBoot", replacements);
    }

    public void sendOtpMessage(String to, String subject, Map<String, Object> replacements) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        String html = "OTP is "+(String) replacements.get("otpnum");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(html, true);

        sender.send(message);
    }
}