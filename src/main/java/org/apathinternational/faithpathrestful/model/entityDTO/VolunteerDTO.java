package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.Volunteer;

import com.fasterxml.jackson.annotation.JsonInclude;

public class VolunteerDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long volunteerId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserAccountDTO userAccount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VolunteerProfileDTO volunteerProfile;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VolunteerAirportPickupDTO volunteerAirportPickup;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VolunteerTempHousingDTO volunteerTempHousing;

    public VolunteerDTO() {
    }

    public VolunteerDTO(Volunteer volunteer) {
        this.volunteerId = volunteer.getId();
        this.userAccount = new UserAccountDTO(volunteer.getUser());
        this.volunteerProfile = new VolunteerProfileDTO(volunteer);
        this.volunteerAirportPickup = new VolunteerAirportPickupDTO(volunteer);
        this.volunteerTempHousing = new VolunteerTempHousingDTO(volunteer);
    }

    public Long getVolunteerId() {
        return volunteerId;
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

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
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

    public void setUserAccount(Volunteer volunteer) {
        this.setUserAccount(new UserAccountDTO(volunteer.getUser()));
    }

    public void setVolunteerProfile(Volunteer volunteer) {
        this.setVolunteerProfile(new VolunteerProfileDTO(volunteer));
    }

    public void setVolunteerAirportPickup(Volunteer volunteer) {
        this.setVolunteerAirportPickup(new VolunteerAirportPickupDTO(volunteer));
    }

    public void setVolunteerTempHousing(Volunteer volunteer) {
        this.setVolunteerTempHousing(new VolunteerTempHousingDTO(volunteer));
    }

    public void setVolunteerId(Volunteer volunteer) {
        this.setVolunteerId(volunteer.getId());
    }
}
