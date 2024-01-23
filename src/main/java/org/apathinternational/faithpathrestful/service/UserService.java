package org.apathinternational.faithpathrestful.service;

import java.util.Optional;

import org.apathinternational.faithpathrestful.model.User;
import org.apathinternational.faithpathrestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
