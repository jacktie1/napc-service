package org.apathinternational.faithpathrestful.model.response;

import java.util.ArrayList;
import java.util.List;

import org.apathinternational.faithpathrestful.entity.Volunteer;
import org.apathinternational.faithpathrestful.model.entityDTO.VolunteerDTO;


public class GetVolunteersResponse {
    private List<VolunteerDTO> volunteers;

    public GetVolunteersResponse() {
    }

    public List<VolunteerDTO> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<VolunteerDTO> volunteers) {
        this.volunteers = volunteers;
    }

    public void setVolunteersFromVolunteerList(List<Volunteer> volunteers) {
        this.volunteers = new ArrayList<VolunteerDTO>();
        
        for(Volunteer volunteer : volunteers)
        {
            VolunteerDTO volunteerDTO = new VolunteerDTO(volunteer);
            this.volunteers.add(volunteerDTO);
        }
    }
    
}
