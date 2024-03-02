package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.AdminProfileDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateAdminProfileRequest {

    @NotNull
    private AdminProfileDTO volunteerProfile;

    public UpdateAdminProfileRequest() {
    }

    public UpdateAdminProfileRequest(AdminProfileDTO volunteerProfile) {
        this.volunteerProfile = volunteerProfile;
    }

    public AdminProfileDTO getAdminProfile() {
        return volunteerProfile;
    }

    public void setAdminProfile(AdminProfileDTO volunteerProfile) {
        this.volunteerProfile = volunteerProfile;
    }


}
