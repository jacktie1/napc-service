package org.apathinternational.faithpathrestful.controller;


import org.apathinternational.faithpathrestful.service.ReferenceService;
import org.apathinternational.faithpathrestful.service.SessionService;
import org.apathinternational.faithpathrestful.service.UserService;
import org.apathinternational.faithpathrestful.model.entityDTO.UserAccountDTO;
import org.apathinternational.faithpathrestful.model.request.DeletesUsersRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateUserAccountRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateUserSecurityQuestionsRequest;
import org.apathinternational.faithpathrestful.model.response.GetUserAccountResponse;
import org.apathinternational.faithpathrestful.model.response.MessageReponse;
import org.apathinternational.faithpathrestful.entity.Reference;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.entity.UserSecurityQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import jakarta.validation.Valid;

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

    @Autowired
    private ReferenceService referenceService;

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

        UserAccountDTO userAccount = new UserAccountDTO(requestedUser);

        GetUserAccountResponse response = new GetUserAccountResponse();
        response.setUserAccount(userAccount);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getAccountByUsername/{username}")
    public ResponseEntity<?> getAccountByUsername(@PathVariable(required=true, name="username") String username) {
        User requestedUser = userService.getUserByUsername(username);

        GetUserAccountResponse response = new GetUserAccountResponse();
        UserAccountDTO userAccount = new UserAccountDTO();

        if(requestedUser != null && requestedUser.getSecurityQuestions() != null && requestedUser.getSecurityQuestions().size() >= 3) {
            userAccount.setSecurityQuestionReferenceId1(requestedUser.getSecurityQuestions().get(0).getSecurityQuestionReference().getId());
            userAccount.setSecurityQuestionReferenceId2(requestedUser.getSecurityQuestions().get(1).getSecurityQuestionReference().getId());
            userAccount.setSecurityQuestionReferenceId3(requestedUser.getSecurityQuestions().get(2).getSecurityQuestionReference().getId());
        } else {
            List<Reference> securityQuestions = referenceService.getReferenceByType("SecurityQuestion");

            //convert 'username' to a long seed
            long seed = 0;
            for (int i = 0; i < username.length(); i++) {
                seed += (long)username.charAt(i);
            }

            // get three security questions randomly
            Random rand = new Random(seed);
            List<Reference> randomSecurityQuestions = new ArrayList<Reference>();

            for (int i = 0; i < 3; i++) {
                int randomIndex = rand.nextInt(securityQuestions.size());
                Reference securityQuestion = securityQuestions.get(randomIndex);
                randomSecurityQuestions.add(securityQuestion);
                securityQuestions.remove(randomIndex);
            }

            userAccount.setSecurityQuestionReferenceId1(randomSecurityQuestions.get(0).getId());
            userAccount.setSecurityQuestionReferenceId2(randomSecurityQuestions.get(1).getId());
            userAccount.setSecurityQuestionReferenceId3(randomSecurityQuestions.get(2).getId());
        }

        response.setUserAccount(userAccount);

        return ResponseHandler.generateResponse(response);
    }
    

    @PutMapping("/updateAccount/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_VOLUNTEER')")
    @Transactional
    public ResponseEntity<?> updateUserAccount(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateUserAccountRequest request) {
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

    @PutMapping("/updateSecurityQuestions/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_VOLUNTEER')")
    @Transactional
    public ResponseEntity<?> updateSecurityQuestions(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateUserSecurityQuestionsRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(!authedUser.isAdmin() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        User userToUpdate = userService.getUserById(userId);

        if(userToUpdate == null) {
            throw new BusinessException("User not found.");
        }

        UserSecurityQuestion userSecurityQuestion1 = new UserSecurityQuestion();
        UserSecurityQuestion userSecurityQuestion2 = new UserSecurityQuestion();
        UserSecurityQuestion userSecurityQuestion3 = new UserSecurityQuestion();

        Reference securityQuestionReference1 = new Reference();
        Reference securityQuestionReference2 = new Reference();
        Reference securityQuestionReference3 = new Reference();

        securityQuestionReference1.setId(request.getSecurityQuestionReferenceId1());
        securityQuestionReference2.setId(request.getSecurityQuestionReferenceId2());
        securityQuestionReference3.setId(request.getSecurityQuestionReferenceId3());
        userSecurityQuestion1.setSecurityQuestionReference(securityQuestionReference1);
        userSecurityQuestion1.setSecurityAnswer(passwordEncoder.encode(request.getSecurityAnswer1().trim().toUpperCase()));
        userSecurityQuestion2.setSecurityQuestionReference(securityQuestionReference2);
        userSecurityQuestion2.setSecurityAnswer(passwordEncoder.encode(request.getSecurityAnswer2().trim().toUpperCase()));
        userSecurityQuestion3.setSecurityQuestionReference(securityQuestionReference3);
        userSecurityQuestion3.setSecurityAnswer(passwordEncoder.encode(request.getSecurityAnswer3().trim().toUpperCase()));
        userSecurityQuestion1.setUser(userToUpdate);
        userSecurityQuestion2.setUser(userToUpdate);
        userSecurityQuestion3.setUser(userToUpdate);

        List<UserSecurityQuestion> securityQuestions = new ArrayList<UserSecurityQuestion>();
        securityQuestions.add(userSecurityQuestion1);
        securityQuestions.add(userSecurityQuestion2);
        securityQuestions.add(userSecurityQuestion3);

        userToUpdate.setSecurityQuestions(securityQuestions);

        return ResponseHandler.generateResponse(new MessageReponse("Account updated successfully."));
    }

    @DeleteMapping("/deleteUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<?> deleteUserAccounts(@Valid @RequestBody DeletesUsersRequest request) {
        List<Long> userIds = request.getUserIds();

        userService.deleteUsers(userIds);

        return ResponseHandler.generateResponse(new MessageReponse("User(s) deleted successfully."));
    }

}