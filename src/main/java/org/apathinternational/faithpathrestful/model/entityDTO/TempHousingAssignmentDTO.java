package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.TempHousingAssignment;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TempHousingAssignmentDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long studentUserId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long volunteerUserId;

    public TempHousingAssignmentDTO() {
    }

    public TempHousingAssignmentDTO(TempHousingAssignment airportPickupAssignment) {
        this.studentUserId = airportPickupAssignment.getStudent().getUser().getId();
        this.volunteerUserId = airportPickupAssignment.getVolunteer().getUser().getId();
    }

    public Long getStudentUserId() {
        return studentUserId;
    }

    public Long getVolunteerUserId() {
        return volunteerUserId;
    }

    public void setStudentUserId(Long studentUserId) {
        this.studentUserId = studentUserId;
    }

    public void setVolunteerUserId(Long volunteerUserId) {
        this.volunteerUserId = volunteerUserId;
    }

}
