package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.Volunteer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class VolunteerProfileDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String gender;

    @NotBlank
    private String affiliation;

    @NotBlank
    @Email
    private String emailAddress;

    @NotBlank
    private String primaryPhoneNumber;

    private String secondaryPhoneNumber;

    private String wechatId;

    private Boolean enabled;

    public VolunteerProfileDTO() {
    }

    public VolunteerProfileDTO(Volunteer volunteer) {
        this.firstName = volunteer.getUser().getFirstName();
        this.lastName = volunteer.getUser().getLastName();
        this.gender = volunteer.getUser().getGender();
        this.affiliation = volunteer.getAffiliation();
        this.emailAddress = volunteer.getUser().getEmailAddress();
        this.primaryPhoneNumber = volunteer.getPrimaryPhoneNumber();
        this.secondaryPhoneNumber = volunteer.getSecondaryPhoneNumber();
        this.wechatId = volunteer.getWechatId();
        this.enabled = volunteer.getUser().getEnabled();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    public String getWechatId() {
        return wechatId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
}
