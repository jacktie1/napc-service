package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.AirportPickupPreference;

public class AirportPickupPreferenceDTO {
    private Long studentUserId;
    private Long volunteerUserId;

    public AirportPickupPreferenceDTO() {
    }

    public AirportPickupPreferenceDTO(AirportPickupPreference airportPickupPreference) {
        this.studentUserId = airportPickupPreference.getStudent().getUser().getId();
        this.volunteerUserId = airportPickupPreference.getVolunteer().getUser().getId();
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
