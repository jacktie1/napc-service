package org.apathinternational.faithpathrestful.common.exception;

import org.apathinternational.faithpathrestful.common.util.Constants;

public class CustomAccessDeniedException extends ApplicationException {
    private final int CODE = Constants.ERROR_CODE_CUSTOM_ACCESS_DENIED_EXCEPTION;

    public CustomAccessDeniedException(String message) {
        super(message);
    }

    public int getCode() {
        return CODE;
    } 
    
}
