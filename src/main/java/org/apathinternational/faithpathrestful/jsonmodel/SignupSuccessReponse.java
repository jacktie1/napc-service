package org.apathinternational.faithpathrestful.jsonmodel;

public class SignupSuccessReponse {
    private String message;

    public SignupSuccessReponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
