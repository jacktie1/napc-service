package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.VolunteerProfileDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.VolunteerTempHousingDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.UserAccountDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.VolunteerAirportPickupDTO;

import jakarta.validation.constraints.NotNull;

public class RegisterVolunteerRequest {

    @NotNull
    private UserAccountDTO userAccount;

    @NotNull
    private VolunteerProfileDTO volunteerProfile;

    @NotNull
    private VolunteerAirportPickupDTO volunteerAirportPickup;

    @NotNull
    private VolunteerTempHousingDTO volunteerTempHousing;


    public RegisterVolunteerRequest() {
    }

    public RegisterVolunteerRequest(UserAccountDTO userAccount, VolunteerProfileDTO volunteerProfile, VolunteerAirportPickupDTO volunteerFlightInfo, VolunteerTempHousingDTO volunteerTempHousing) {
        this.userAccount = userAccount;
        this.volunteerProfile = volunteerProfile;
        this.volunteerAirportPickup = volunteerFlightInfo;
        this.volunteerTempHousing = volunteerTempHousing;
    }

    public UserAccountDTO getUserAccount() {
        return userAccount;
    }

    public VolunteerProfileDTO getVolunteerProfile() {
        return volunteerProfile;
    }

    public VolunteerAirportPickupDTO getVolunteerAirportPickup() {
        return volunteerAirportPickup;
    }

    public VolunteerTempHousingDTO getVolunteerTempHousing() {
        return volunteerTempHousing;
    }

    public void setUserAccount(UserAccountDTO userAccount) {
        this.userAccount = userAccount;
    }

    public void setVolunteerProfile(VolunteerProfileDTO volunteerProfile) {
        this.volunteerProfile = volunteerProfile;
    }

    public void setVolunteerAirportPickup(VolunteerAirportPickupDTO volunteerAirportPickup) {
        this.volunteerAirportPickup = volunteerAirportPickup;
    }

    public void setVolunteerTempHousing(VolunteerTempHousingDTO volunteerTempHousing) {
        this.volunteerTempHousing = volunteerTempHousing;
    }

}
