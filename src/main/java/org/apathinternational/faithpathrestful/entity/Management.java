package org.apathinternational.faithpathrestful.entity;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "management")
public class Management extends AuditableEntity {
    @Id
    @Column(name = "management_id", nullable = false)
    private Long id;

    @Column(name = "does_assignment_start", nullable = false)
    private Boolean doesAssignmentStart;

    @Column(name = "student_registration_start_date", nullable = false)
    private LocalDate studentRegistrationStartDate;

    @Column(name = "student_registration_end_date", nullable = false)
    private LocalDate studentRegistrationEndDate;

    @Column(name = "announcement")
    private String announcement;

    @Column(name = "week_of_welcome_start_date", nullable = false)
    private LocalDate weekOfWelcomeStartDate;

    public Management() {
    }

    public Management(Long id, Boolean doesAssignmentStart, LocalDate studentRegistrationStartDate, LocalDate studentRegistrationEndDate, String announcement, LocalDate weekOfWelcomeStartDate) {
        this.id = id;
        this.doesAssignmentStart = doesAssignmentStart;
        this.studentRegistrationStartDate = studentRegistrationStartDate;
        this.studentRegistrationEndDate = studentRegistrationEndDate;
        this.announcement = announcement;
        this.weekOfWelcomeStartDate = weekOfWelcomeStartDate;
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

    public LocalDate getWeekOfWelcomeStartDate() {
        return weekOfWelcomeStartDate;
    }

    public void setWeekOfWelcomeStartDate(LocalDate weekOfWelcomeStartDate) {
        this.weekOfWelcomeStartDate = weekOfWelcomeStartDate;
    }
    
}
