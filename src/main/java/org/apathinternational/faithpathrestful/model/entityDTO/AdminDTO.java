package org.apathinternational.faithpathrestful.model.entityDTO;

import org.apathinternational.faithpathrestful.entity.Administrator;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AdminDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long adminId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserAccountDTO userAccount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AdminProfileDTO adminProfile;

    public AdminDTO() {
    }

    public AdminDTO(Administrator admin) {
        this.adminId = admin.getId();
        this.userAccount = new UserAccountDTO(admin.getUser());
        this.adminProfile = new AdminProfileDTO(admin);
    }

    public Long getAdminId() {
        return adminId;
    }

    public UserAccountDTO getUserAccount() {
        return userAccount;
    }

    public AdminProfileDTO getAdminProfile() {
        return adminProfile;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public void setUserAccount(UserAccountDTO userAccount) {
        this.userAccount = userAccount;
    }

    public void setAdminProfile(AdminProfileDTO adminProfile) {
        this.adminProfile = adminProfile;
    }

    public void setUserAccountFromAdminEntity(Administrator admin) {
        this.setUserAccount(new UserAccountDTO(admin.getUser()));
    }

    public void setAdminProfileFromAdminEntity(Administrator admin) {
        this.setAdminProfile(new AdminProfileDTO(admin));
    }

    public void setAdminIdFromAdminEntity(Administrator admin) {
        this.setAdminId(admin.getId());
    }
}
