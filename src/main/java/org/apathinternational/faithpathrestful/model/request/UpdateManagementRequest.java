package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.ManagementDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateManagementRequest {

    @NotNull
    private ManagementDTO Management;

    public UpdateManagementRequest() {
    }

    public UpdateManagementRequest(ManagementDTO Management) {
        this.Management = Management;
    }

    public ManagementDTO getManagement() {
        return Management;
    }

    public void setManagement(ManagementDTO Management) {
        this.Management = Management;
    }


}
