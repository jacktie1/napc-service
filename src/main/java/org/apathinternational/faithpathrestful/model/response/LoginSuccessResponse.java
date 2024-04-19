package org.apathinternational.faithpathrestful.model.response;

public class LoginSuccessResponse {
    private String token;
    private Long userId;
    private String role;
    private String firstName;
    private String lastName;
    private Boolean hasSecurityQuestions;

    public LoginSuccessResponse() {
    }

    public LoginSuccessResponse(String token, Long userId, String role, String firstName, String lastName, Boolean hasSecurityQuestions) {
        this.token = token;
        this.userId = userId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasSecurityQuestions = hasSecurityQuestions;
    }

    public Long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getHasSecurityQuestions() {
        return hasSecurityQuestions;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHasSecurityQuestions(Boolean hasSecurityQuestions) {
        this.hasSecurityQuestions = hasSecurityQuestions;
    }
}
