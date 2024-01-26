package org.apathinternational.faithpathrestful.service;

import java.util.Optional;

import org.apathinternational.faithpathrestful.model.User;
import org.apathinternational.faithpathrestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElse(null);
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // This method is to get the currently logged in user
    public User getAuthedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        
        return this.getUserByUsername(username);
    }
}
