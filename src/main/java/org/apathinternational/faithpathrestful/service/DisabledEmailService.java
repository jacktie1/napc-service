package org.apathinternational.faithpathrestful.service;

import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.response.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
    prefix = "apath",
    name = "email.enabled",
    havingValue = "false",
    matchIfMissing = true
)
public class DisabledEmailService implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public void sendAirportPickupAssignmentUpdateMessage(User toUser) {
        String toEmail = toUser.getEmailAddress();

        logger.info("Skipped airport pickup assignment update message planned to send to " + toEmail + " due to email service being disabled.");
        return;
    }

    @Override
    public void sendTempHousingAssignmentUpdateMessage(User toUser) {
        String toEmail = toUser.getEmailAddress();

        logger.info("Skipped Temporary housing assignment update message planned to send to " + toEmail + " due to email service being disabled.");
        return;
    }
}