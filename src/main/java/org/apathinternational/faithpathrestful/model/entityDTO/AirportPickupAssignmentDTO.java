package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.AirportPickupAssignment;
import org.apathinternational.faithpathrestful.entity.Student;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AirportPickupAssignmentDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long studentUserId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long volunteerUserId;

    // Lazy field
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentDTO student;

    public AirportPickupAssignmentDTO() {
    }

    public AirportPickupAssignmentDTO(AirportPickupAssignment airportPickupAssignment) {
        this.studentUserId = airportPickupAssignment.getStudent().getUser().getId();
        this.volunteerUserId = airportPickupAssignment.getVolunteer().getUser().getId();
    }

    public Long getStudentUserId() {
        return studentUserId;
    }

    public Long getVolunteerUserId() {
        return volunteerUserId;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudentUserId(Long studentUserId) {
        this.studentUserId = studentUserId;
    }

    public void setVolunteerUserId(Long volunteerUserId) {
        this.volunteerUserId = volunteerUserId;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public void setStudentFromStudentEntity(Student student) {
        this.student = new StudentDTO(student);
    }

}
