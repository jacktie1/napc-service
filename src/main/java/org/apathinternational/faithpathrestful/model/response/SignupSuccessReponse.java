package org.apathinternational.faithpathrestful.model.response;

public class SignupSuccessReponse {
    private String message;

    public SignupSuccessReponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
