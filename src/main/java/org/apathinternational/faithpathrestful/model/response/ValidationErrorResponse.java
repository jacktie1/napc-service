package org.apathinternational.faithpathrestful.model.response;

import java.util.Map;

public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> fieldErrors;

    public ValidationErrorResponse() {
    }

    public ValidationErrorResponse(int code, String message, Map<String, String> fieldErrors) {
        super(code, message);
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void getFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }    
}
