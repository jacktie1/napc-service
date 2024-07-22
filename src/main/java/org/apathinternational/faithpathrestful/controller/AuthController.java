package org.apathinternational.faithpathrestful.controller;

import org.apathinternational.faithpathrestful.security.DatabaseUserDetailsService;
import org.apathinternational.faithpathrestful.security.JwtTokenProvider;
import org.apathinternational.faithpathrestful.service.UserService;
import org.apathinternational.faithpathrestful.model.request.LoginRequest;
import org.apathinternational.faithpathrestful.model.request.ResetPasswordRequest;
import org.apathinternational.faithpathrestful.model.request.ValidateUserSecurityAnswersRequest;
import org.apathinternational.faithpathrestful.model.response.LoginSuccessResponse;
import org.apathinternational.faithpathrestful.model.response.MessageReponse;
import org.apathinternational.faithpathrestful.model.response.ValidateUserSecurityAnswersSuccessResponse;
import org.apathinternational.faithpathrestful.entity.User;

import java.util.HashMap;
import java.util.Map;

import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.response.ResponseHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    @Autowired
    private DatabaseUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) throws Exception {
        // The 'username' of UserDetails instance is actually the user id because username can change
        User userByUsername = userService.getUserByUsername(request.getUsername());

        if (userByUsername == null) {
            throw new BusinessException("Invalid username or password.");
        }

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userByUsername.getId(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BusinessException("Invalid username or password.");
        } catch (DisabledException e) {
            // User disabled
            throw new BusinessException("User account is disabled. Please contact support for assistance.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userByUsername.getId().toString());

        User authedUser = userService.getUserById(Long.parseLong(userDetails.getUsername()));

        String token = jwtTokenProvider.createToken(userDetails.getUsername());

        String role = authedUser.getRole().getName();
        Long userId = authedUser.getId();
        String firstName = authedUser.getFirstName();
        String lastName = authedUser.getLastName();
        
        // Check if user has security questions
        Boolean hasSecurityQuestions = authedUser.getSecurityQuestions() != null && authedUser.getSecurityQuestions().size() >= 3;

        userService.saveUserLogin(authedUser);
    
        return ResponseHandler.generateResponse(new LoginSuccessResponse(token, userId, role, firstName, lastName, hasSecurityQuestions));
    }

    @PostMapping("/validateUserSecurityAnswers")
    public ResponseEntity<?> validateUserSecurityAnswers(@Valid @RequestBody ValidateUserSecurityAnswersRequest request) throws Exception {
        User user = userService.getUserByUsername(request.getUsername());

        if (user == null) {
            throw new BusinessException("Invalid username or security answers.");
        }

        if(user.getSecurityQuestions() == null || user.getSecurityQuestions().size() < 3) {
            throw new BusinessException("Invalid username or security answers.");
        }

        Map<Long, String> securityQuestionAnswers = new HashMap<Long, String>();

        for(int i = 0; i < user.getSecurityQuestions().size(); i++) {
            securityQuestionAnswers.put(
                user.getSecurityQuestions().get(i).getSecurityQuestionReference().getId(),
                user.getSecurityQuestions().get(i).getSecurityAnswer()
                );
        }

        String expectedSecurityAnwser1 = securityQuestionAnswers.get(request.getSecurityQuestionReferenceId1());
        String expectedSecurityAnwser2 = securityQuestionAnswers.get(request.getSecurityQuestionReferenceId2());
        String expectedSecurityAnwser3 = securityQuestionAnswers.get(request.getSecurityQuestionReferenceId3());
        String securityAnswer1 = request.getSecurityAnswer1().trim().toUpperCase();
        String securityAnswer2 = request.getSecurityAnswer2().trim().toUpperCase();
        String securityAnswer3 = request.getSecurityAnswer3().trim().toUpperCase();

        if(!passwordEncoder.matches(securityAnswer1, expectedSecurityAnwser1) ||
            !passwordEncoder.matches(securityAnswer2, expectedSecurityAnwser2) ||
            !passwordEncoder.matches(securityAnswer3, expectedSecurityAnwser3)) {
            throw new BusinessException("Invalid username or security answers.");
        }

        String token = jwtTokenProvider.createToken(user.getId().toString());

        ValidateUserSecurityAnswersSuccessResponse response = new ValidateUserSecurityAnswersSuccessResponse(token);

        return ResponseHandler.generateResponse(response);
    }

        
    @PutMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        String token = request.getToken();
        String password = request.getPassword();

        if(!jwtTokenProvider.validateToken(token)) {
            throw new BusinessException("Invalid token.");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(token);

        User user = userService.getUserById(userId);

        if(user == null) {
            throw new BusinessException("User not found.");
        }

        user.setPassword(passwordEncoder.encode(password));

        userService.updateUser(user);

        return ResponseHandler.generateResponse(new MessageReponse("Password reset successfully."));
    }

}