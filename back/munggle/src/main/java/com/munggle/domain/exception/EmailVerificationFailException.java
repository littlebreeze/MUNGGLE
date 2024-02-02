package com.munggle.domain.exception;

import org.springframework.mail.MailException;

public class EmailVerificationFailException extends MailException {

    public EmailVerificationFailException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
