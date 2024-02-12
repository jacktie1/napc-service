package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.Volunteer;

import jakarta.validation.constraints.NotNull;

public class VolunteerAirportPickupDTO {
    @NotNull
    private Boolean providesAirportPickup;

    private String carManufacturer;

    private String carModel;

    private Integer numCarSeats;

    private Integer numMaxLgLuggages;

    private Integer numMaxTrips;

    private String airportPickupComment;
    
    public VolunteerAirportPickupDTO() {
    }

    public VolunteerAirportPickupDTO(Volunteer volunteer) {
        this.providesAirportPickup = volunteer.getProvidesAirportPickup();
        this.carManufacturer = volunteer.getCarManufacturer();
        this.carModel = volunteer.getCarModel();
        this.numCarSeats = volunteer.getNumCarSeats();
        this.numMaxLgLuggages = volunteer.getNumMaxLgLuggages();
        this.numMaxTrips = volunteer.getNumMaxTrips();
        this.airportPickupComment = volunteer.getAirportPickupComment();
    }

    public Boolean getProvidesAirportPickup() {
        return providesAirportPickup;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public String getCarModel() {
        return carModel;
    }

    public Integer getNumCarSeats() {
        return numCarSeats;
    }

    public Integer getNumMaxLgLuggages() {
        return numMaxLgLuggages;
    }

    public Integer getNumMaxTrips() {
        return numMaxTrips;
    }

    public String getAirportPickupComment() {
        return airportPickupComment;
    }

    public void setProvidesAirportPickup(Boolean providesAirportPickup) {
        this.providesAirportPickup = providesAirportPickup;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setNumCarSeats(Integer numCarSeats) {
        this.numCarSeats = numCarSeats;
    }

    public void setNumMaxLgLuggages(Integer numMaxLgLuggages) {
        this.numMaxLgLuggages = numMaxLgLuggages;
    }

    public void setNumMaxTrips(Integer numMaxTrips) {
        this.numMaxTrips = numMaxTrips;
    }

    public void setAirportPickupComment(String airportPickupComment) {
        this.airportPickupComment = airportPickupComment;
    }
    
}