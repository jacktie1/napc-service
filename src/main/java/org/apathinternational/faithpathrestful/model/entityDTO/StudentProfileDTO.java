package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.Student;
import org.apathinternational.faithpathrestful.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentProfileDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String englishName;

    @NotBlank
    private String gender;

    @NotNull
    private Boolean isNewStudent;

    @NotBlank
    private String studentType;

    private String graduatesFrom;

    private Long majorReferenceId;

    private String customMajor;

    @NotNull
    private Boolean hasCompanion;

    @Email
    private String emailAddress;

    @NotBlank
    private String wechatId;

    private String cnPhoneNumber;

    private String usPhoneNumber;

    @NotNull
    private Boolean attendsWeekOfWelcome;

    public StudentProfileDTO() {
    }

    public StudentProfileDTO(Student student) {
        User studentUser = student.getUser();
        this.firstName = studentUser.getFirstName();
        this.lastName = studentUser.getLastName();
        this.englishName = student.getEnglishName();
        this.gender = studentUser.getGender();
        this.isNewStudent = student.getIsNewStudent();
        this.studentType = student.getStudentType();
        this.graduatesFrom = student.getGraduatesFrom();

        if(student.getMajorReference() != null)
        {
            this.majorReferenceId = student.getMajorReference().getId();
        }
        
        this.customMajor = student.getCustomMajor();
        this.hasCompanion = student.getHasCompanion();
        this.emailAddress = studentUser.getEmailAddress();
        this.wechatId = student.getWechatId();
        this.cnPhoneNumber = student.getCnPhoneNumber();
        this.usPhoneNumber = student.getUsPhoneNumber();
        this.attendsWeekOfWelcome = student.getAttendsWeekOfWelcome();
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getGender() {
        return gender;
    }

    public Boolean getIsNewStudent() {
        return isNewStudent;
    }

    public String getStudentType() {
        return studentType;
    }

    public String getGraduatesFrom() {
        return graduatesFrom;
    }

    public Long getMajorReferenceId() {
        return majorReferenceId;
    }

    public String getCustomMajor() {
        return customMajor;
    }

    public Boolean getHasCompanion() {
        return hasCompanion;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getWechatId() {
        return wechatId;
    }

    public String getCnPhoneNumber() {
        return cnPhoneNumber;
    }

    public String getUsPhoneNumber() {
        return usPhoneNumber;
    }

    public Boolean getAttendsWeekOfWelcome() {
        return attendsWeekOfWelcome;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIsNewStudent(Boolean isNewStudent) {
        this.isNewStudent = isNewStudent;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public void setGraduatesFrom(String graduatesFrom) {
        this.graduatesFrom = graduatesFrom;
    }

    public void setMajorReferenceId(Long majorReferenceId) {
        this.majorReferenceId = majorReferenceId;
    }

    public void setCustomMajor(String customMajor) {
        this.customMajor = customMajor;
    }

    public void setHasCompanion(Boolean hasCompanion) {
        this.hasCompanion = hasCompanion;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public void setCnPhoneNumber(String cnPhoneNumber) {
        this.cnPhoneNumber = cnPhoneNumber;
    }

    public void setUsPhoneNumber(String usPhoneNumber) {
        this.usPhoneNumber = usPhoneNumber;
    }

    public void setAttendsWeekOfWelcome(Boolean attendsWeekOfWelcome) {
        this.attendsWeekOfWelcome = attendsWeekOfWelcome;
    }

}
