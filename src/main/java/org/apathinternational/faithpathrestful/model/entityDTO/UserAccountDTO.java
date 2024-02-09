package org.apathinternational.faithpathrestful.model.entityDTO;

import java.util.List;

import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.entity.UserSecurityQuestion;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;

public class UserAccountDTO {
    private Long userId;

    @NotBlank
    private String username;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long securityQuestionReferenceUserId1;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String securityAnswer1;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long securityQuestionReferenceUserId2;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String securityAnswer2;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long securityQuestionReferenceUserId3;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String securityAnswer3;

    public UserAccountDTO() {
    }

    public UserAccountDTO(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();

        List<UserSecurityQuestion> securityQuestions = user.getSecurityQuestions();

        this.securityQuestionReferenceUserId1 = securityQuestions.get(0).getSecurityQuestionReference().getId();
        this.securityQuestionReferenceUserId2 = securityQuestions.get(1).getSecurityQuestionReference().getId();
        this.securityQuestionReferenceUserId3 = securityQuestions.get(2).getSecurityQuestionReference().getId();
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getSecurityQuestionReferenceId1() {
        return securityQuestionReferenceUserId1;
    }

    public String getsecurityAnswer1() {
        return securityAnswer1;
    }

    public Long getSecurityQuestionReferenceId2() {
        return securityQuestionReferenceUserId2;
    }

    public String getsecurityAnswer2() {
        return securityAnswer2;
    }

    public Long getSecurityQuestionReferenceId3() {
        return securityQuestionReferenceUserId3;
    }

    public String getsecurityAnswer3() {
        return securityAnswer3;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityQuestionReferenceId1(Long securityQuestionReferenceUserId1) {
        this.securityQuestionReferenceUserId1 = securityQuestionReferenceUserId1;
    }

    public void setsecurityAnswer1(String securityAnswer1) {
        this.securityAnswer1 = securityAnswer1;
    }

    public void setSecurityQuestionReferenceId2(Long securityQuestionReferenceUserId2) {
        this.securityQuestionReferenceUserId2 = securityQuestionReferenceUserId2;
    }

    public void setsecurityAnswer2(String securityAnswer2) {
        this.securityAnswer2 = securityAnswer2;
    }

    public void setSecurityQuestionReferenceId3(Long securityQuestionReferenceUserId3) {
        this.securityQuestionReferenceUserId3 = securityQuestionReferenceUserId3;
    }

    public void setsecurityAnswer3(String securityAnswer3) {
        this.securityAnswer3 = securityAnswer3;
    }
}
