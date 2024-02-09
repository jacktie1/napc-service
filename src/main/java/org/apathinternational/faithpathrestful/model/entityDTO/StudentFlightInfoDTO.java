package org.apathinternational.faithpathrestful.model.entityDTO;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class StudentFlightInfoDTO {
    @NotNull
    private Boolean needsAirportPickup;

    private Boolean HasFlightInfo;

    private String arrivalFlightNumber;

    private Long arrivalAirlineReferenceId;

    private String customArrivalAirline;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalDatetime;

    private String departureFlightNumber;

    private Long departureAirlineReferenceId;

    private String customDepartureAirline;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureDatetime;

    private Integer numLgLuggages;

    private Integer numSmLuggages;

    public StudentFlightInfoDTO() {
    }

    public StudentFlightInfoDTO(Boolean needsAirportPickup, Boolean hasFlightInfo, String arrivalFlightNumber, Long arrivalAirlineReferenceId, String customArrivalAirline, LocalDateTime arrivalDatetime, String departureFlightNumber, Long departureAirlineReferenceId, String customDepartureAirline, LocalDateTime departureDatetime, Integer numLgLuggages, Integer numSmLuggages) {
        this.needsAirportPickup = needsAirportPickup;
        HasFlightInfo = hasFlightInfo;
        this.arrivalFlightNumber = arrivalFlightNumber;
        this.arrivalAirlineReferenceId = arrivalAirlineReferenceId;
        this.customArrivalAirline = customArrivalAirline;
        this.arrivalDatetime = arrivalDatetime;
        this.departureFlightNumber = departureFlightNumber;
        this.departureAirlineReferenceId = departureAirlineReferenceId;
        this.customDepartureAirline = customDepartureAirline;
        this.departureDatetime = departureDatetime;
        this.numLgLuggages = numLgLuggages;
        this.numSmLuggages = numSmLuggages;
    }

    public Boolean getNeedsAirportPickup() {
        return needsAirportPickup;
    }

    public Boolean getHasFlightInfo() {
        return HasFlightInfo;
    }

    public String getArrivalFlightNumber() {
        return arrivalFlightNumber;
    }

    public Long getArrivalAirlineReferenceId() {
        return arrivalAirlineReferenceId;
    }

    public String getCustomArrivalAirline() {
        return customArrivalAirline;
    }

    public LocalDateTime getArrivalDatetime() {
        return arrivalDatetime;
    }

    public String getDepartureFlightNumber() {
        return departureFlightNumber;
    }

    public Long getDepartureAirlineReferenceId() {
        return departureAirlineReferenceId;
    }

    public String getCustomDepartureAirline() {
        return customDepartureAirline;
    }

    public LocalDateTime getDepartureDatetime() {
        return departureDatetime;
    }

    public Integer getNumLgLuggages() {
        return numLgLuggages;
    }

    public Integer getNumSmLuggages() {
        return numSmLuggages;
    }

    public void setNeedsAirportPickup(Boolean needsAirportPickup) {
        this.needsAirportPickup = needsAirportPickup;
    }

    public void setHasFlightInfo(Boolean hasFlightInfo) {
        HasFlightInfo = hasFlightInfo;
    }

    public void setArrivalFlightNumber(String arrivalFlightNumber) {
        this.arrivalFlightNumber = arrivalFlightNumber;
    }

    public void setArrivalAirlineReferenceId(Long arrivalAirlineReferenceId) {
        this.arrivalAirlineReferenceId = arrivalAirlineReferenceId;
    }

    public void setCustomArrivalAirline(String customArrivalAirline) {
        this.customArrivalAirline = customArrivalAirline;
    }

    public void setArrivalDatetime(LocalDateTime arrivalDatetime) {
        this.arrivalDatetime = arrivalDatetime;
    }

    public void setDepartureFlightNumber(String departureFlightNumber) {
        this.departureFlightNumber = departureFlightNumber;
    }

    public void setDepartureAirlineReferenceId(Long departureAirlineReferenceId) {
        this.departureAirlineReferenceId = departureAirlineReferenceId;
    }

    public void setCustomDepartureAirline(String customDepartureAirline) {
        this.customDepartureAirline = customDepartureAirline;
    }

    public void setDepartureDatetime(LocalDateTime departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public void setNumLgLuggages(Integer numLgLuggages) {
        this.numLgLuggages = numLgLuggages;
    }

    public void setNumSmLuggages(Integer numSmLuggages) {
        this.numSmLuggages = numSmLuggages;
    }
}
