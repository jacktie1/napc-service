package org.apathinternational.faithpathrestful.model.response;

import org.apathinternational.faithpathrestful.entity.Volunteer;
import org.apathinternational.faithpathrestful.model.entityDTO.VolunteerDTO;


public class GetVolunteerResponse {
    private VolunteerDTO volunteer;

    public GetVolunteerResponse() {
    }

    public VolunteerDTO getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(VolunteerDTO volunteer) {
        this.volunteer = volunteer;
    }

    public void setUserAccount(Volunteer volunteer) {
        if(this.volunteer == null)
        {
            this.volunteer = new VolunteerDTO();
        }

        this.volunteer.setUserAccountFromVolunteerEntity(volunteer);
    }

    public void setVolunteerProfile(Volunteer volunteer) {
        if(this.volunteer == null)
        {
            this.volunteer = new VolunteerDTO();
        }

        this.volunteer.setVolunteerProfileFromVolunteerEntity(volunteer);
    }

    public void setVolunteerAirportPickup(Volunteer volunteer) {
        if(this.volunteer == null)
        {
            this.volunteer = new VolunteerDTO();
        }

        this.volunteer.setVolunteerAirportPickupFromVolunteerEntity(volunteer);
    }

    public void setVolunteerTempHousing(Volunteer volunteer) {
        if(this.volunteer == null)
        {
            this.volunteer = new VolunteerDTO();
        }

        this.volunteer.setVolunteerTempHousingFromVolunteerEntity(volunteer);
    }
    
}
