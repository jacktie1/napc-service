package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.Student;
import org.apathinternational.faithpathrestful.entity.TempHousingAssignment;
import org.apathinternational.faithpathrestful.entity.Volunteer;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TempHousingAssignmentDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long studentUserId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long volunteerUserId;

    // Lazy fields
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StudentDTO student;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VolunteerDTO volunteer;

    public TempHousingAssignmentDTO() {
    }

    public TempHousingAssignmentDTO(TempHousingAssignment tempHousingAssignment) {
        this.studentUserId = tempHousingAssignment.getStudent().getUser().getId();
        this.volunteerUserId = tempHousingAssignment.getVolunteer().getUser().getId();
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

    public VolunteerDTO getVolunteer() {
        return volunteer;
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

    public void setVolunteer(VolunteerDTO volunteer) {
        this.volunteer = volunteer;
    }

    public void setStudentFromStudentEntity(Student student) {
        this.student = new StudentDTO(student);
    }

    public void setVolunteerFromVolunteerEntity(Volunteer volunteer) {
        this.volunteer = new VolunteerDTO(volunteer);
    }

}
