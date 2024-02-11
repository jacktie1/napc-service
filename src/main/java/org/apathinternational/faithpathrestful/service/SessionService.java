package org.apathinternational.faithpathrestful.service;

import org.apathinternational.faithpathrestful.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private UserService userService;

    // This method is to get the currently logged in user
    public User getAuthedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // anonymous user
        if(authentication.getPrincipal() instanceof String) {
            return null;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();
        
        return userService.getUserById(Long.parseLong(userId));
    }

    // This method is to get the currently logged in user
    public Long getAuthedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // anonymous user
        if(authentication.getPrincipal() instanceof String) {
            return null;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if(userDetails == null) {
            return null;
        }

        String userId = userDetails.getUsername();
        
        return Long.parseLong(userId);
    }
}
