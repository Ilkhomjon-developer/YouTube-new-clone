package com.example.demo.service;

import com.example.demo.mapper.EmailMapper;
import com.example.demo.util.RandomCodeGenerator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailSenderService {

    @Value("${spring.mail.username}")
    private String fromAccount;

    private final EmailHistoryService emailHistoryService;
    private final EmailMapper EMAIL_MAPPER;
    private final JavaMailSender javaMailSender;


    public void sendEmail(String to){
        Integer code = RandomCodeGenerator.generatedCode();
        final String body = "Your verification code is: %s".formatted(code);
        sendMimeMessage(to, body, code);
    }

    public void resendEmailCode(String email) {
        sendEmail(email);
    }
    private void sendMimeMessage(String toAccount, String body, Integer code) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setFrom(fromAccount);
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setSubject("Email Verification");
            helper.setTo(toAccount);
            helper.setText(body, false);
            javaMailSender.send(msg);
            emailHistoryService.save(EMAIL_MAPPER.toEmailEntity(toAccount,body,code));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
