package org.apathinternational.faithpathrestful.model.request;

import java.util.List;

import org.apathinternational.faithpathrestful.model.entityDTO.AirportPickupPreferenceDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateAirportPickupPreferencesRequest {
    @NotNull
    private List<AirportPickupPreferenceDTO> airportPickupPreferences;

    public UpdateAirportPickupPreferencesRequest() {
    }

    public List<AirportPickupPreferenceDTO> getAirportPickupPreferences() {
        return airportPickupPreferences;
    }

    public void setAirportPickupPreferences(List<AirportPickupPreferenceDTO> airportPickupPreferences) {
        this.airportPickupPreferences = airportPickupPreferences;
    }
}
