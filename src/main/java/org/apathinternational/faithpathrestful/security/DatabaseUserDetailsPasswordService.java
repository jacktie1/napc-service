package org.apathinternational.faithpathrestful.security;

import java.util.ArrayList;

import org.apathinternational.faithpathrestful.model.User;
import org.apathinternational.faithpathrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
class DatabaseUserDetailsPasswordService implements UserDetailsPasswordService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String newPassword) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(userDetails.getUsername());

        if(user == null)
        {
            throw new UsernameNotFoundException("User not found with username: " + userDetails.getUsername());
        }

        
        user.setPassword(newPassword);
        
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
