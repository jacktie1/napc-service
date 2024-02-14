package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.VolunteerAirportPickupDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateVolunteerAirportPickupRequest {


    @NotNull
    private VolunteerAirportPickupDTO VolunteerAirportPickup;

    public UpdateVolunteerAirportPickupRequest() {
    }

    public UpdateVolunteerAirportPickupRequest(VolunteerAirportPickupDTO VolunteerAirportPickup) {
        this.VolunteerAirportPickup = VolunteerAirportPickup;
    }

    public VolunteerAirportPickupDTO getVolunteerAirportPickup() {
        return VolunteerAirportPickup;
    }

    public void setVolunteerAirportPickup(VolunteerAirportPickupDTO VolunteerAirportPickup) {
        this.VolunteerAirportPickup = VolunteerAirportPickup;
    }


}
