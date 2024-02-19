package org.apathinternational.faithpathrestful.model.request;

import java.util.List;

import org.apathinternational.faithpathrestful.model.entityDTO.AirportPickupAssignmentDTO;

public class UpdateVolunteerAirportPickupAssignmentsRequest {
    private List<AirportPickupAssignmentDTO> airportPickupAssignments;

    public UpdateVolunteerAirportPickupAssignmentsRequest() {
    }

    public List<AirportPickupAssignmentDTO> getAirportPickupAssignments() {
        return airportPickupAssignments;
    }

    public void setAirportPickupAssignments(List<AirportPickupAssignmentDTO> airportPickupAssignments) {
        this.airportPickupAssignments = airportPickupAssignments;
    }
}
