package com.erpak.barter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    public void sendSimpleEmail(String toEmail,
                                String body,
                                String subject) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("takasetapp@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);

    }

}
