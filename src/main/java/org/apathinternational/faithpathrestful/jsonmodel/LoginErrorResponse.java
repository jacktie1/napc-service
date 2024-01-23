package org.apathinternational.faithpathrestful.jsonmodel;

public class LoginErrorResponse {
    private String message;

    public LoginErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
