package org.apathinternational.faithpathrestful.model.request;

public class SignupRequest {
    private String username;
    private String password;
    private String emailAddress;
    private String role;

    public SignupRequest() {
    }

    public SignupRequest(String username, String password, String emailAddress, String role) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.role = role;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password= password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
