package org.apathinternational.faithpathrestful.common.exception;

import java.util.Map;

import org.apathinternational.faithpathrestful.common.util.Constants;

public class ValidationException extends ApplicationException {
    private final int CODE = Constants.ERROR_CODE_VALIDATION_EXCEPTION;

    private Map<String, String> fieldErrors;

    public ValidationException() {
        super("Validation error");
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Map<String, String> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }

    public int getCode() {
        return CODE;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
