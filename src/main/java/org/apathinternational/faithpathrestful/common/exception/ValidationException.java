package org.apathinternational.faithpathrestful.common.exception;

import org.apathinternational.faithpathrestful.common.util.Constants;

public class ValidationException extends ApplicationException {
    private final int CODE = Constants.ERROR_CODE_VALIDATION_EXCEPTION;

    public ValidationException(String message) {
        super(message);
    }

    public int getCode() {
        return CODE;
    }
}
