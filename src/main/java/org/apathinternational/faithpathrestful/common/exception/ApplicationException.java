package org.apathinternational.faithpathrestful.common.exception;

import org.apathinternational.faithpathrestful.common.util.Constants;

public class ApplicationException extends RuntimeException {
    private final int CODE = Constants.ERROR_CODE_APPLICATION_EXCEIPTION;

    public ApplicationException(String message) {
        super(message);
    }

    public int getCode() {
        return CODE;
    }    
}
