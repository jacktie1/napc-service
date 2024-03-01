package org.apathinternational.faithpathrestful.service;

import org.apathinternational.faithpathrestful.entity.User;

public interface EmailService {
    void sendAirportPickupAssignmentUpdateMessage(User toUser);
    void sendTempHousingAssignmentUpdateMessage(User toUser);
}