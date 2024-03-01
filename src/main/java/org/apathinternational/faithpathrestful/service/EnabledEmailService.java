package org.apathinternational.faithpathrestful.service;

import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.response.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@ConditionalOnProperty(
    prefix = "apath",
    name = "email.enabled",
    havingValue = "true",
    matchIfMissing = false
)
public class EnabledEmailService implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${email.redirect}")
    private String redirectToEmail;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private String redirectToEmail(String toEmail, StringBuilder subjectBuilder) {
        if(redirectToEmail != null && !redirectToEmail.isEmpty()) {
            subjectBuilder.append(" (Redirected from " + toEmail + ")");
            
            return redirectToEmail;
        } else {
            // If the active profile is dev, log an error if no redirect email is set
            if(activeProfile.equals("dev")) {
                logger.error("No redirect email found. Please set the dev redirect email in the application.properties file.");
                return null;
            }

            // If no redirect email is set, return the original email
            return toEmail;
        }
    }

    @Override
    public void sendAirportPickupAssignmentUpdateMessage(User toUser) {
        String toEmail = toUser.getEmailAddress();

        StringBuilder subjectBuilder = new StringBuilder();
        subjectBuilder.append("[Apath Notification] Airport Pickup Assignment Update");
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append(toUser.getFirstName() + " " + toUser.getLastName() + ",\n\n");
        textBuilder.append("Your airport pickup assignment has been updated. Please check the Apath website for more details and contact our coordinator if you have any questions.\n\n");
        textBuilder.append("(This is an automated message. Please do not reply to this email)\n\n");
        textBuilder.append("Thank you,\nApath - International");

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String finalToEmail = redirectToEmail(toEmail, subjectBuilder);

        if(finalToEmail == null) {
            return;
        }

        try {
            helper.setFrom(fromEmail);
            helper.setTo(finalToEmail);
            helper.setSubject(subjectBuilder.toString());
            helper.setText(textBuilder.toString());
        } catch (MessagingException e) {
            logger.error("Error sending airport pickup assignment update message: ", e);
        }
        emailSender.send(message);
    }

    @Override
    public void sendTempHousingAssignmentUpdateMessage(User toUser) {
        String toEmail = toUser.getEmailAddress();

        StringBuilder subjectBuilder = new StringBuilder();
        subjectBuilder.append("[Apath Notification] Temporary Housing Assignment Update");
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append(toUser.getFirstName() + " " + toUser.getLastName() + ",\n\n");
        textBuilder.append("Your temporary housing assignment has been updated. Please check the Apath website for more details and contact our coordinator if you have any questions.\n\n");
        textBuilder.append("(This is an automated message. Please do not reply to this email)\n\n");
        textBuilder.append("Thank you,\nApath - International");

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String finalToEmail = redirectToEmail(toEmail, subjectBuilder);

        if(finalToEmail == null) {
            return;
        }

        try {
            helper.setFrom(fromEmail);
            helper.setTo(finalToEmail);
            helper.setSubject(subjectBuilder.toString());
            helper.setText(textBuilder.toString());
        } catch (MessagingException e) {
            logger.error("Error sending temporary housing assignment update message: ", e);
        }
        emailSender.send(message);
    }
}