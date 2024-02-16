package org.apathinternational.faithpathrestful.model.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DeletesUsersRequest {
    @NotNull
    @NotEmpty
    private List<Long> userIds;

    public DeletesUsersRequest() {
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public DeletesUsersRequest(List<Long> userIds) {
        this.userIds = userIds;
    }    
    
}
