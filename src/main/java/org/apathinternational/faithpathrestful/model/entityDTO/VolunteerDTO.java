package org.apathinternational.faithpathrestful.model.entityDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apathinternational.faithpathrestful.entity.AirportPickupAssignment;
import org.apathinternational.faithpathrestful.entity.AirportPickupPreference;
import org.apathinternational.faithpathrestful.entity.TempHousingAssignment;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date modifiedAt;

    // Lazy fields
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AirportPickupPreferenceDTO> airportPickupPreferences;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AirportPickupAssignmentDTO> airportPickupAssignments;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TempHousingAssignmentDTO> tempHousingAssignments;

    public VolunteerDTO() {
    }

    public VolunteerDTO(Volunteer volunteer) {
        this.volunteerId = volunteer.getId();
        this.userAccount = new UserAccountDTO(volunteer.getUser());
        this.volunteerProfile = new VolunteerProfileDTO(volunteer);
        this.volunteerAirportPickup = new VolunteerAirportPickupDTO(volunteer);
        this.volunteerTempHousing = new VolunteerTempHousingDTO(volunteer);

        Date volunteerModifiedAt = volunteer.getModifiedAt();
        Date userModifiedAt = volunteer.getUser().getModifiedAt();

        // Set the modifiedAt to the latest modifiedAt
        if (volunteerModifiedAt.after(userModifiedAt)) {
            this.modifiedAt = volunteerModifiedAt;
        } else {
            this.modifiedAt = userModifiedAt;
        }
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

    public List<AirportPickupPreferenceDTO> getAirportPickupPreferences() {
        return airportPickupPreferences;
    }

    public List<AirportPickupAssignmentDTO> getAirportPickupAssignments() {
        return airportPickupAssignments;
    }

    public List<TempHousingAssignmentDTO> getTempHousingAssignments() {
        return tempHousingAssignments;
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

    public void setAirportPickupPreferences(List<AirportPickupPreferenceDTO> airportPickupPreferences) {
        this.airportPickupPreferences = airportPickupPreferences;
    }

    public void setAirportPickupAssignments(List<AirportPickupAssignmentDTO> airportPickupAssignments) {
        this.airportPickupAssignments = airportPickupAssignments;
    }

    public void setTempHousingAssignments(List<TempHousingAssignmentDTO> tempHousingAssignments) {
        this.tempHousingAssignments = tempHousingAssignments;
    }

    public void setUserAccountFromVolunteerEntity(Volunteer volunteer) {
        this.setUserAccount(new UserAccountDTO(volunteer.getUser()));
    }

    public void setVolunteerProfileFromVolunteerEntity(Volunteer volunteer) {
        this.setVolunteerProfile(new VolunteerProfileDTO(volunteer));
    }

    public void setVolunteerAirportPickupFromVolunteerEntity(Volunteer volunteer) {
        this.setVolunteerAirportPickup(new VolunteerAirportPickupDTO(volunteer));
    }

    public void setVolunteerTempHousingFromVolunteerEntity(Volunteer volunteer) {
        this.setVolunteerTempHousing(new VolunteerTempHousingDTO(volunteer));
    }

    public void setVolunteerIdFromVolunteerEntity(Volunteer volunteer) {
        this.setVolunteerId(volunteer.getId());
    }

    public void setAirportPickupPreferencesFromVolunteerEntity(Volunteer volunteer) {
        List<AirportPickupPreferenceDTO> airportPickupPreferenceDTOs = new ArrayList<>();

        for (AirportPickupPreference airportPickupPreference : volunteer.getAirportPickupPreferences()) {
            AirportPickupPreferenceDTO airportPickupPreferenceDTO = new AirportPickupPreferenceDTO(airportPickupPreference);
            airportPickupPreferenceDTOs.add(airportPickupPreferenceDTO);
        }

        this.setAirportPickupPreferences(airportPickupPreferenceDTOs);
    }

    public void setAirportPickupAssignmentsFromVolunteerEntity(Volunteer volunteer) {
        List<AirportPickupAssignmentDTO> airportPickupAssignmentDTOs = new ArrayList<>();

        for (AirportPickupAssignment airportPickupAssignment : volunteer.getAirportPickupAssignments()) {
            AirportPickupAssignmentDTO airportPickupAssignmentDTO = new AirportPickupAssignmentDTO(airportPickupAssignment);
            airportPickupAssignmentDTOs.add(airportPickupAssignmentDTO);
        }

        this.setAirportPickupAssignments(airportPickupAssignmentDTOs);
    }

    public void setTempHousingAssignmentsFromVolunteerEntity(Volunteer volunteer) {
        List<TempHousingAssignmentDTO> tempHousingAssignmentDTOs = new ArrayList<>();

        for (TempHousingAssignment tempHousingAssignment : volunteer.getTempHousingAssignments()) {
            TempHousingAssignmentDTO tempHousingAssignmentDTO = new TempHousingAssignmentDTO(tempHousingAssignment);
            tempHousingAssignmentDTOs.add(tempHousingAssignmentDTO);
        }

        this.setTempHousingAssignments(tempHousingAssignmentDTOs);
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }
}
