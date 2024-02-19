package org.apathinternational.faithpathrestful.model.response;

import java.util.List;

import org.apathinternational.faithpathrestful.model.entityDTO.AirportPickupAssignmentDTO;

public class GetAirportPickupAssignmentsResponse {
    private List<AirportPickupAssignmentDTO> airportPickupAssignments;

    public GetAirportPickupAssignmentsResponse() {
    }

    public List<AirportPickupAssignmentDTO> getAirportPickupAssignments() {
        return airportPickupAssignments;
    }

    public void setAirportPickupAssignments(List<AirportPickupAssignmentDTO> airportPickupAssignments) {
        this.airportPickupAssignments = airportPickupAssignments;
    }
    
}
