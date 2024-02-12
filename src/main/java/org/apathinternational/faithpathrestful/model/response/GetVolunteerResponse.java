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

        this.volunteer.setUserAccount(volunteer);
    }

    public void setVolunteerProfile(Volunteer volunteer) {
        if(this.volunteer == null)
        {
            this.volunteer = new VolunteerDTO();
        }

        this.volunteer.setVolunteerProfile(volunteer);
    }

    public void setVolunteerAirportPickup(Volunteer volunteer) {
        if(this.volunteer == null)
        {
            this.volunteer = new VolunteerDTO();
        }

        this.volunteer.setVolunteerAirportPickup(volunteer);
    }

    public void setVolunteerTempHousing(Volunteer volunteer) {
        if(this.volunteer == null)
        {
            this.volunteer = new VolunteerDTO();
        }

        this.volunteer.setVolunteerTempHousing(volunteer);
    }
    
}
