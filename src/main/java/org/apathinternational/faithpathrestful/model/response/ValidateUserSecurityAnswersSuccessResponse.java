package org.apathinternational.faithpathrestful.model.response;

public class ValidateUserSecurityAnswersSuccessResponse {
    private String token;

    public ValidateUserSecurityAnswersSuccessResponse() {
    }

    public ValidateUserSecurityAnswersSuccessResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
