package org.apathinternational.faithpathrestful.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apathinternational.faithpathrestful.common.exception.ValidationException;
import org.apathinternational.faithpathrestful.entity.Reference;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.entity.UserSecurityQuestion;
import org.apathinternational.faithpathrestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReferenceService referenceService;

    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElse(null);
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }
    
    public User createUser(User user) {
        Map<String, String> fieldErrors = new HashMap<String, String>();

        if(user.getUsername() != null)
        {
            User existingUser = getUserByUsername(user.getUsername());

            if(existingUser != null)
            {
                fieldErrors.put("username", "Username already exists. Please choose a different username.");
            }
        }

        // New user is enabled by default
        if(user.getEnabled() == null)
        {
            user.setEnabled(true);
        }

        List<UserSecurityQuestion> securityQuestions = user.getSecurityQuestions();

        for(UserSecurityQuestion securityQuestion : securityQuestions)
        {
            Reference securityQuestionReference = securityQuestion.getSecurityQuestionReference();

            if(securityQuestionReference != null)
            {
                Reference savedSecurityQuestionReference = referenceService.findById(securityQuestionReference.getId());

                if(savedSecurityQuestionReference != null)
                {
                    securityQuestion.setSecurityQuestionReference(savedSecurityQuestionReference);
                }
                else
                {
                    fieldErrors.put("securityQuestionReferenceId", "Invalid security question reference. Please check the security question reference and try again.");
                }
            }

            securityQuestion.setUser(user);
        }

        if(!fieldErrors.isEmpty())
        {
            throw new ValidationException("Validation error(s) encountered during volunteer creation", fieldErrors);
        }

        return userRepository.save(user);
    }

    public void deleteUsers(List<Long> userIds) {
        userRepository.deleteAllById(userIds);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
