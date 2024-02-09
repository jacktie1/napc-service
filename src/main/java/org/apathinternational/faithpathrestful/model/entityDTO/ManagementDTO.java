package org.apathinternational.faithpathrestful.model.entityDTO;

import java.time.LocalDate;

import org.apathinternational.faithpathrestful.entity.Management;


public class ManagementDTO {
    private Long id;
    private Boolean doesAssignmentStart;
    private LocalDate studentRegistrationStartDate;
    private LocalDate studentRegistrationEndDate;
    private String announcement;

    public ManagementDTO() {
    }

    public ManagementDTO(Management management) {
        this.id = management.getId();
        this.doesAssignmentStart = management.getDoesAssignmentStart();
        this.studentRegistrationStartDate = management.getStudentRegistrationStartDate();
        this.studentRegistrationEndDate = management.getStudentRegistrationEndDate();
        this.announcement = management.getAnnouncement();
    }

    public ManagementDTO(Long id, Boolean doesAssignmentStart, LocalDate studentRegistrationStartDate, LocalDate studentRegistrationEndDate, String announcement) {
        this.id = id;
        this.doesAssignmentStart = doesAssignmentStart;
        this.studentRegistrationStartDate = studentRegistrationStartDate;
        this.studentRegistrationEndDate = studentRegistrationEndDate;
        this.announcement = announcement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
