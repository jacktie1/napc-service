package org.apathinternational.faithpathrestful.model.request;

import java.util.List;

import org.apathinternational.faithpathrestful.model.entityDTO.TempHousingAssignmentDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateVolunteerTempHousingAssignmentsRequest {
    @NotNull
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
