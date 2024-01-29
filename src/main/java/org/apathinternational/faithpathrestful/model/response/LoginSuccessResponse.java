package org.apathinternational.faithpathrestful.model.response;

public class LoginSuccessResponse {
    private String token;
    private String role;

    public LoginSuccessResponse() {
    }

    public LoginSuccessResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
