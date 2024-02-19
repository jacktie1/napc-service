package org.apathinternational.faithpathrestful.model.response;

import java.util.List;

import org.apathinternational.faithpathrestful.model.entityDTO.AirportPickupPreferenceDTO;

public class GetAirportPickupPreferencesResponse {
    private List<AirportPickupPreferenceDTO> airportPickupPreferences;

    public GetAirportPickupPreferencesResponse() {
    }

    public List<AirportPickupPreferenceDTO> getAirportPickupPreferences() {
        return airportPickupPreferences;
    }

    public void setAirportPickupPreferences(List<AirportPickupPreferenceDTO> airportPickupPreferences) {
        this.airportPickupPreferences = airportPickupPreferences;
    }
    
}
