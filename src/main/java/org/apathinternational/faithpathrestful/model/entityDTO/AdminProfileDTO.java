package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.Administrator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AdminProfileDTO {
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

    public AdminProfileDTO() {
    }

    public AdminProfileDTO(Administrator admin) {
        this.firstName = admin.getUser().getFirstName();
        this.lastName = admin.getUser().getLastName();
        this.gender = admin.getUser().getGender();
        this.affiliation = admin.getAffiliation();
        this.emailAddress = admin.getUser().getEmailAddress();
        this.primaryPhoneNumber = admin.getPrimaryPhoneNumber();
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
    
}
