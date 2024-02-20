package org.apathinternational.faithpathrestful.model.response;

import java.util.List;

import org.apathinternational.faithpathrestful.model.entityDTO.TempHousingAssignmentDTO;

public class GetTempHousingAssignmentsResponse {
    private List<TempHousingAssignmentDTO> airportPickupAssignments;

    public GetTempHousingAssignmentsResponse() {
    }

    public List<TempHousingAssignmentDTO> getTempHousingAssignments() {
        return airportPickupAssignments;
    }

    public void setTempHousingAssignments(List<TempHousingAssignmentDTO> airportPickupAssignments) {
        this.airportPickupAssignments = airportPickupAssignments;
    }
    
}
