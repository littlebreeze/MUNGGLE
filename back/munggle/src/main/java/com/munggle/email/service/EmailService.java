package com.munggle.email.service;

import com.munggle.domain.exception.EmailVerificationFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.munggle.domain.exception.ExceptionMessage.MAIL_NOT_VALID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(text);

        return simpleMailMessage;
    }

    public void  sendEmail(String toEmail,
                           String title,
                           String text) {
        SimpleMailMessage simpleMailMessage = createEmailForm(toEmail, title, text);
        try {
            emailSender.send(simpleMailMessage);
        } catch (MailException e) {
            log.debug("MailService.sendEmail exception occur toEmail: {}, " +
                    "title: {}, text: {}", toEmail, title, text);
            throw new EmailVerificationFailException(MAIL_NOT_VALID);
        }
    }
}
