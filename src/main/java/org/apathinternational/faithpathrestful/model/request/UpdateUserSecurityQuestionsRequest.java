package org.apathinternational.faithpathrestful.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateUserSecurityQuestionsRequest {
    @NotNull
    private Long securityQuestionReferenceId1;

    @NotBlank
    private String securityAnswer1;

    @NotNull
    private Long securityQuestionReferenceId2;

    @NotBlank
    private String securityAnswer2;

    @NotNull
    private Long securityQuestionReferenceId3;

    @NotBlank
    private String securityAnswer3;

    public UpdateUserSecurityQuestionsRequest() {
    }

    public void setSecurityQuestionReferenceId1(Long securityQuestionReferenceId1) {
        this.securityQuestionReferenceId1 = securityQuestionReferenceId1;
    }

    public void setSecurityAnswer1(String securityAnswer1) {
        this.securityAnswer1 = securityAnswer1;
    }

    public void setSecurityQuestionReferenceId2(Long securityQuestionReferenceId2) {
        this.securityQuestionReferenceId2 = securityQuestionReferenceId2;
    }

    public void setSecurityAnswer2(String securityAnswer2) {
        this.securityAnswer2 = securityAnswer2;
    }

    public void setSecurityQuestionReferenceId3(Long securityQuestionReferenceId3) {
        this.securityQuestionReferenceId3 = securityQuestionReferenceId3;
    }

    public void setSecurityAnswer3(String securityAnswer3) {
        this.securityAnswer3 = securityAnswer3;
    }

    public Long getSecurityQuestionReferenceId1() {
        return securityQuestionReferenceId1;
    }

    public String getSecurityAnswer1() {
        return securityAnswer1;
    }

    public Long getSecurityQuestionReferenceId2() {
        return securityQuestionReferenceId2;
    }

    public String getSecurityAnswer2() {
        return securityAnswer2;
    }

    public Long getSecurityQuestionReferenceId3() {
        return securityQuestionReferenceId3;
    }

    public String getSecurityAnswer3() {
        return securityAnswer3;
    }    
}
