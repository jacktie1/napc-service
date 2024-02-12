package org.apathinternational.faithpathrestful.service;

import java.util.HashMap;
import java.util.Map;

import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.common.exception.ValidationException;
import org.apathinternational.faithpathrestful.entity.Role;
import org.apathinternational.faithpathrestful.entity.Volunteer;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    public Volunteer createVolunteer(Volunteer volunteer) {
        Map<String, String> fieldErrors = new HashMap<String, String>();

        User volunteerUser = volunteer.getUser();

        if(userService.getUserByUsername(volunteerUser.getUsername()) != null)
        {
            throw new BusinessException("Username already exists. Please choose a different username and try again.");
        }

        Role role = roleService.getRoleByName("Volunteer");

        if(role == null)
        {
            throw new BusinessException("Invalid role. Please check the role and try again.");
        }

        if(!fieldErrors.isEmpty())
        {
            throw new ValidationException("Validation error(s) encountered during volunteer creation", fieldErrors);
        }

        volunteerUser.setRole(role);

        User savedUser = userService.createUser(volunteerUser);
    
        volunteer.setUser(savedUser);
        
        return volunteerRepository.save(volunteer);
    }

    public Volunteer getVolunteerByUserId(Long userId) {
        User volunteerUser = userService.getUserById(userId);

        if(volunteerUser == null)
        {
            throw new BusinessException("User not found. Please check the user and try again.");
        }

        if(volunteerUser.isVolunteer())
        {
            return volunteerUser.getVolunteer();
        }
        else
        {
            throw new BusinessException("User is not a volunteer. Please check the user and try again.");
        }
    }
}
