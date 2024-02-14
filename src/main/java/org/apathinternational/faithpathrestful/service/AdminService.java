package org.apathinternational.faithpathrestful.service;

import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.entity.Administrator;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private UserService userService;


    public Administrator getAdminByUserId(Long userId) {
        User adminUser = userService.getUserById(userId);

        if(adminUser == null)
        {
            throw new BusinessException("User not found. Please check the user and try again.");
        }

        if(adminUser.isAdmin())
        {
            return adminUser.getAdministrator();
        }
        else
        {
            throw new BusinessException("User is not a admin. Please check the user and try again.");
        }
    }
}
