package org.apathinternational.faithpathrestful.model.response;

public class LoginSuccessResponse {
    private String token;
    private Long userId;
    private String role;
    private String firstName;
    private String lastName;

    public LoginSuccessResponse() {
    }

    public LoginSuccessResponse(String token, Long userId, String role, String firstName, String lastName) {
        this.token = token;
        this.userId = userId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
