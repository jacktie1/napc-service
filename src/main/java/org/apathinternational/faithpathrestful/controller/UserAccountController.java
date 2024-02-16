package org.apathinternational.faithpathrestful.controller;


import org.apathinternational.faithpathrestful.service.SessionService;
import org.apathinternational.faithpathrestful.service.UserService;
import org.apathinternational.faithpathrestful.model.entityDTO.UserAccountDTO;
import org.apathinternational.faithpathrestful.model.request.DeletesUsersRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateUserAccountRequest;
import org.apathinternational.faithpathrestful.model.response.GetUserAccountResponse;
import org.apathinternational.faithpathrestful.model.response.MessageReponse;
import org.apathinternational.faithpathrestful.entity.User;

import java.util.List;

import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.common.exception.CustomAccessDeniedException;
import org.apathinternational.faithpathrestful.response.ResponseHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/userAccount")
@Validated
public class UserAccountController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/getAccount/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_VOLUNTEER')")
    public ResponseEntity<?> getAccount(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(!authedUser.isAdmin() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to access this resource.");
        }

        User requestedUser = userService.getUserById(userId);

        if(requestedUser == null) {
            throw new BusinessException("User not found.");
        }

        GetUserAccountResponse response = new GetUserAccountResponse(requestedUser);

        return ResponseHandler.generateResponse(response);
    }

    @PutMapping("/updateAccount/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_VOLUNTEER')")
    @Transactional
    public ResponseEntity<?> updateUserAccount(@PathVariable(required=true, name="userId") Long userId, @RequestBody UpdateUserAccountRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(!authedUser.isAdmin() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        User userToUpdate = userService.getUserById(userId);

        if(userToUpdate == null) {
            throw new BusinessException("User not found.");
        }

        User userByUserName = userService.getUserByUsername(request.getUserAccount().getUsername());

        if(userByUserName != null && !userByUserName.getId().equals(userId)) {
            throw new BusinessException("Username already exists. Please choose a different username and try again.");
        }

        UserAccountDTO userAccount = request.getUserAccount();

        if(userAccount.getUsername() != null) {
            userToUpdate.setUsername(userAccount.getUsername());
        }

        if(userAccount.getPassword() != null) {
            userToUpdate.setPassword(passwordEncoder.encode(userAccount.getPassword()));   
        }

        return ResponseHandler.generateResponse(new MessageReponse("Account updated successfully."));
    }

    @DeleteMapping("/deleteUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<?> deleteUserAccounts(@RequestBody DeletesUsersRequest request) {
        List<Long> userIds = request.getUserIds();

        userService.deleteUsers(userIds);

        return ResponseHandler.generateResponse(new MessageReponse("User(s) deleted successfully."));
    }

}