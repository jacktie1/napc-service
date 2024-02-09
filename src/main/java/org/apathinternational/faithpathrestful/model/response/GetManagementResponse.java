package org.apathinternational.faithpathrestful.model.response;

import org.apathinternational.faithpathrestful.entity.Management;
import org.apathinternational.faithpathrestful.model.entityDTO.ManagementDTO;

public class GetManagementResponse {
    private ManagementDTO management;

    public GetManagementResponse() {
    }

    public GetManagementResponse(Management management) {
        this.management = new ManagementDTO(management);
    }

    public ManagementDTO getManagement() {
        return management;
    }

    public void setManagement(ManagementDTO management) {
        this.management = management;
    }
    
}
