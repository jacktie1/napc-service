package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.VolunteerProfileDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateVolunteerProfileRequest {


    @NotNull
    private VolunteerProfileDTO volunteerProfile;

    public UpdateVolunteerProfileRequest() {
    }

    public UpdateVolunteerProfileRequest(VolunteerProfileDTO volunteerProfile) {
        this.volunteerProfile = volunteerProfile;
    }

    public VolunteerProfileDTO getVolunteerProfile() {
        return volunteerProfile;
    }

    public void setVolunteerProfile(VolunteerProfileDTO volunteerProfile) {
        this.volunteerProfile = volunteerProfile;
    }


}
