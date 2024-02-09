package org.apathinternational.faithpathrestful.model.entityDTO;

import jakarta.validation.constraints.NotBlank;

public class UserAccountDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private Long securityQuestionReferenceId1;

    @NotBlank
    private String securityAnswer1;

    @NotBlank
    private Long securityQuestionReferenceId2;

    @NotBlank
    private String securityAnswer2;

    @NotBlank
    private Long securityQuestionReferenceId3;

    @NotBlank
    private String securityAnswer3;

    public UserAccountDTO() {
    }

    public UserAccountDTO(String username, String password, Long securityQuestionReferenceId1, String securityAnswer1, Long securityQuestionReferenceId2, String securityAnswer2, Long securityQuestionReferenceId3, String securityAnswer3) {
        this.username = username;
        this.password = password;
        this.securityQuestionReferenceId1 = securityQuestionReferenceId1;
        this.securityAnswer1 = securityAnswer1;
        this.securityQuestionReferenceId2 = securityQuestionReferenceId2;
        this.securityAnswer2 = securityAnswer2;
        this.securityQuestionReferenceId3 = securityQuestionReferenceId3;
        this.securityAnswer3 = securityAnswer3;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getSecurityQuestionReferenceId1() {
        return securityQuestionReferenceId1;
    }

    public String getsecurityAnswer1() {
        return securityAnswer1;
    }

    public Long getSecurityQuestionReferenceId2() {
        return securityQuestionReferenceId2;
    }

    public String getsecurityAnswer2() {
        return securityAnswer2;
    }

    public Long getSecurityQuestionReferenceId3() {
        return securityQuestionReferenceId3;
    }

    public String getsecurityAnswer3() {
        return securityAnswer3;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityQuestionReferenceId1(Long securityQuestionReferenceId1) {
        this.securityQuestionReferenceId1 = securityQuestionReferenceId1;
    }

    public void setsecurityAnswer1(String securityAnswer1) {
        this.securityAnswer1 = securityAnswer1;
    }

    public void setSecurityQuestionReferenceId2(Long securityQuestionReferenceId2) {
        this.securityQuestionReferenceId2 = securityQuestionReferenceId2;
    }

    public void setsecurityAnswer2(String securityAnswer2) {
        this.securityAnswer2 = securityAnswer2;
    }

    public void setSecurityQuestionReferenceId3(Long securityQuestionReferenceId3) {
        this.securityQuestionReferenceId3 = securityQuestionReferenceId3;
    }

    public void setsecurityAnswer3(String securityAnswer3) {
        this.securityAnswer3 = securityAnswer3;
    }
}
