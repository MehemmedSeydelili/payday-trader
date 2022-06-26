package az.expressbank.paydaytrade.service;

public interface EmailService {
    void sendMail(String toEmail, String subject, String message);
}
