package org.apathinternational.faithpathrestful.model.entityDTO;

import java.time.LocalDate;

import org.apathinternational.faithpathrestful.entity.Volunteer;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;

public class VolunteerTempHousingDTO {
    @NonNull
    private Boolean providesTempHousing;

    @NotBlank
    private String homeAddress;

    private Integer numMaxStudentsHosted;

    private LocalDate tempHousingStartDate;

    private LocalDate tempHousingEndDate;

    private Integer numDoubleBeds;

    private Integer numSingleBeds;

    private String genderPreference;

    private Boolean providesRide;

    private Boolean hasPet;

    private String petDescription;

    private String tempHousingComment;

    public VolunteerTempHousingDTO() {
    }

    public VolunteerTempHousingDTO(Volunteer volunteer) {
        this.providesTempHousing = volunteer.getProvidesTempHousing();
        this.homeAddress = volunteer.getHomeAddress();
        this.numMaxStudentsHosted = volunteer.getNumMaxStudentsHosted();
        this.tempHousingStartDate = volunteer.getTempHousingStartDate();
        this.tempHousingEndDate = volunteer.getTempHousingEndDate();
        this.numDoubleBeds = volunteer.getNumDoubleBeds();
        this.numSingleBeds = volunteer.getNumSingleBeds();
        this.genderPreference = volunteer.getGenderPreference();
        this.providesRide = volunteer.getProvidesRide();
        this.hasPet = volunteer.getHasPet();
        this.petDescription = volunteer.getPetDescription();
        this.tempHousingComment = volunteer.getTempHousingComment();
    }

    public Boolean getProvidesTempHousing() {
        return providesTempHousing;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public Integer getNumMaxStudentsHosted() {
        return numMaxStudentsHosted;
    }

    public LocalDate getTempHousingStartDate() {
        return tempHousingStartDate;
    }

    public LocalDate getTempHousingEndDate() {
        return tempHousingEndDate;
    }

    public Integer getNumDoubleBeds() {
        return numDoubleBeds;
    }

    public Integer getNumSingleBeds() {
        return numSingleBeds;
    }

    public String getGenderPreference() {
        return genderPreference;
    }

    public Boolean getProvidesRide() {
        return providesRide;
    }

    public Boolean getHasPet() {
        return hasPet;
    }

    public String getPetDescription() {
        return petDescription;
    }

    public String getTempHousingComment() {
        return tempHousingComment;
    }

    public void setProvidesTempHousing(Boolean providesTempHousing) {
        this.providesTempHousing = providesTempHousing;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setNumMaxStudentsHosted(Integer numMaxStudentsHosted) {
        this.numMaxStudentsHosted = numMaxStudentsHosted;
    }

    public void setTempHousingStartDate(LocalDate tempHousingStartDate) {
        this.tempHousingStartDate = tempHousingStartDate;
    }

    public void setTempHousingEndDate(LocalDate tempHousingEndDate) {
        this.tempHousingEndDate = tempHousingEndDate;
    }

    public void setNumDoubleBeds(Integer numDoubleBeds) {
        this.numDoubleBeds = numDoubleBeds;
    }

    public void setNumSingleBeds(Integer numSingleBeds) {
        this.numSingleBeds = numSingleBeds;
    }

    public void setGenderPreference(String genderPreference) {
        this.genderPreference = genderPreference;
    }

    public void setProvidesRides(Boolean providesRide) {
        this.providesRide = providesRide;
    }

    public void setHasPet(Boolean hasPet) {
        this.hasPet = hasPet;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }

    public void setTempHousingComment(String tempHousingComment) {
        this.tempHousingComment = tempHousingComment;
    }
    
}
