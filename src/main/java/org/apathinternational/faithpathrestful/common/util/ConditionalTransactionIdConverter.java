package org.apathinternational.faithpathrestful.common.util;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.MDC;

// This class is used to add transId field conditionally to the logback pattern
public class ConditionalTransactionIdConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        String transactionId = MDC.get("transId");
        return transactionId != null ? "[TransId: " + transactionId + "] " : "";
    }
}