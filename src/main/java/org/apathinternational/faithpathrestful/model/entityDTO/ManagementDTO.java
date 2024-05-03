package org.apathinternational.faithpathrestful.model.entityDTO;

import java.time.LocalDate;

import org.apathinternational.faithpathrestful.entity.Management;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ManagementDTO {
    private Long managementId;

    @NotNull
    private Boolean doesAssignmentStart;

    @NotNull
    private LocalDate studentRegistrationStartDate;

    @NotNull
    private LocalDate studentRegistrationEndDate;

    @NotBlank
    private String announcement;

    @NotBlank
    private LocalDate weekOfWelcomeStartDate;

    public ManagementDTO() {
    }

    public ManagementDTO(Management management) {
        this.managementId = management.getId();
        this.doesAssignmentStart = management.getDoesAssignmentStart();
        this.studentRegistrationStartDate = management.getStudentRegistrationStartDate();
        this.studentRegistrationEndDate = management.getStudentRegistrationEndDate();
        this.announcement = management.getAnnouncement();
        this.weekOfWelcomeStartDate = management.getWeekOfWelcomeStartDate();
    }

    public ManagementDTO(Long managementId, Boolean doesAssignmentStart, LocalDate studentRegistrationStartDate, LocalDate studentRegistrationEndDate, String announcement, LocalDate weekOfWelcomeStartDate) {
        this.managementId = managementId;
        this.doesAssignmentStart = doesAssignmentStart;
        this.studentRegistrationStartDate = studentRegistrationStartDate;
        this.studentRegistrationEndDate = studentRegistrationEndDate;
        this.announcement = announcement;
        this.weekOfWelcomeStartDate = weekOfWelcomeStartDate;
    }

    public Long getManagementId() {
        return managementId;
    }

    public void setManagementId(Long managementId) {
        this.managementId = managementId;
    }

    public Boolean getDoesAssignmentStart() {
        return doesAssignmentStart;
    }

    public void setDoesAssignmentStart(Boolean doesAssignmentStart) {
        this.doesAssignmentStart = doesAssignmentStart;
    }

    public LocalDate getStudentRegistrationStartDate() {
        return studentRegistrationStartDate;
    }

    public void setStudentRegistrationStartDate(LocalDate studentRegistrationStartDate) {
        this.studentRegistrationStartDate = studentRegistrationStartDate;
    }

    public LocalDate getStudentRegistrationEndDate() {
        return studentRegistrationEndDate;
    }

    public void setStudentRegistrationEndDate(LocalDate studentRegistrationEndDate) {
        this.studentRegistrationEndDate = studentRegistrationEndDate;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public LocalDate getWeekOfWelcomeStartDate() {
        return weekOfWelcomeStartDate;
    }

    public void setWeekOfWelcomeStartDate(LocalDate weekOfWelcomeStartDate) {
        this.weekOfWelcomeStartDate = weekOfWelcomeStartDate;
    }

}
