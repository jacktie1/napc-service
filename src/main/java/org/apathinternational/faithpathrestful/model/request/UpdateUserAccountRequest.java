package org.apathinternational.faithpathrestful.model.request;

import org.apathinternational.faithpathrestful.model.entityDTO.UserAccountDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateUserAccountRequest {


    @NotNull
    private UserAccountDTO userAccount;

    public UpdateUserAccountRequest() {
    }

    public UpdateUserAccountRequest(UserAccountDTO userAccount) {
        this.userAccount = userAccount;
    }

    public UserAccountDTO getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountDTO userAccount) {
        this.userAccount = userAccount;
    }


}
