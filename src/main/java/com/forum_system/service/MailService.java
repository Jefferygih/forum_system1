package com.forum_system.service;


import javax.mail.MessagingException;

public interface MailService {

    void sendHtmlMessage(String to, String subject, String text) throws MessagingException;

    void sendActivateEmail(String to, String username, String activateCode) throws MessagingException;

    void sendValidationEmail(String to, String username, String validateCode) throws MessagingException;

    void sendResetPasswordEmail(String to, String username, String newPassword) throws MessagingException;
}
