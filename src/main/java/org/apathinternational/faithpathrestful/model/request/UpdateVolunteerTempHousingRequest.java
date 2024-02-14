package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.VolunteerTempHousingDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateVolunteerTempHousingRequest {


    @NotNull
    private VolunteerTempHousingDTO VolunteerTempHousing;

    public UpdateVolunteerTempHousingRequest() {
    }

    public UpdateVolunteerTempHousingRequest(VolunteerTempHousingDTO VolunteerTempHousing) {
        this.VolunteerTempHousing = VolunteerTempHousing;
    }

    public VolunteerTempHousingDTO getVolunteerTempHousing() {
        return VolunteerTempHousing;
    }

    public void setVolunteerTempHousing(VolunteerTempHousingDTO VolunteerTempHousing) {
        this.VolunteerTempHousing = VolunteerTempHousing;
    }


}
