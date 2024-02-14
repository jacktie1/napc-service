package org.apathinternational.faithpathrestful.model.response;

import org.apathinternational.faithpathrestful.entity.Administrator;
import org.apathinternational.faithpathrestful.model.entityDTO.AdminDTO;


public class GetAdminResponse {
    private AdminDTO volunteer;

    public GetAdminResponse() {
    }

    public AdminDTO getAdmin() {
        return volunteer;
    }

    public void setAdmin(AdminDTO volunteer) {
        this.volunteer = volunteer;
    }

    public void setUserAccount(Administrator volunteer) {
        if(this.volunteer == null)
        {
            this.volunteer = new AdminDTO();
        }

        this.volunteer.setUserAccount(volunteer);
    }

    public void setAdminProfile(Administrator volunteer) {
        if(this.volunteer == null)
        {
            this.volunteer = new AdminDTO();
        }

        this.volunteer.setAdminProfile(volunteer);
    }
}
