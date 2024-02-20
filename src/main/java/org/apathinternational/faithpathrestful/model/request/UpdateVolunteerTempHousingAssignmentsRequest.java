package org.apathinternational.faithpathrestful.model.request;

import java.util.List;

import org.apathinternational.faithpathrestful.model.entityDTO.TempHousingAssignmentDTO;

public class UpdateVolunteerTempHousingAssignmentsRequest {
    private List<TempHousingAssignmentDTO> airportPickupAssignments;

    public UpdateVolunteerTempHousingAssignmentsRequest() {
    }

    public List<TempHousingAssignmentDTO> getTempHousingAssignments() {
        return airportPickupAssignments;
    }

    public void setTempHousingAssignments(List<TempHousingAssignmentDTO> airportPickupAssignments) {
        this.airportPickupAssignments = airportPickupAssignments;
    }
}
