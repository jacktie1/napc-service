package org.apathinternational.faithpathrestful.model.response;

import org.apathinternational.faithpathrestful.entity.Administrator;
import org.apathinternational.faithpathrestful.model.entityDTO.AdminDTO;


public class GetAdminResponse {
    private AdminDTO admin;

    public GetAdminResponse() {
    }

    public AdminDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdminDTO admin) {
        this.admin = admin;
    }

    public void setUserAccount(Administrator admin) {
        if(this.admin == null)
        {
            this.admin = new AdminDTO();
        }

        this.admin.setUserAccountFromAdminEntity(admin);
    }

    public void setAdminProfile(Administrator admin) {
        if(this.admin == null)
        {
            this.admin = new AdminDTO();
        }

        this.admin.setAdminProfileFromAdminEntity(admin);
    }
}
