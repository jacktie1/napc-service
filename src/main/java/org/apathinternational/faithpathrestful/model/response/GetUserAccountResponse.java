package org.apathinternational.faithpathrestful.model.response;

import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.model.entityDTO.UserAccountDTO;

public class GetUserAccountResponse {
    private UserAccountDTO userAccount;

    public GetUserAccountResponse() {
    }

    public GetUserAccountResponse(User user) {
        this.userAccount = new UserAccountDTO(user);
    }

    public UserAccountDTO getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountDTO userAccount) {
        this.userAccount = userAccount;
    }

}
