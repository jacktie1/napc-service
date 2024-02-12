package org.apathinternational.faithpathrestful.controller;

import org.apathinternational.faithpathrestful.security.DatabaseUserDetailsService;
import org.apathinternational.faithpathrestful.security.JwtTokenProvider;
import org.apathinternational.faithpathrestful.service.UserService;

import org.apathinternational.faithpathrestful.model.response.LoginSuccessResponse;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.response.ResponseHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) throws Exception {
        // The 'username' of UserDetails instance is actually the user id because username can change
        User userByUsername = userService.getUserByUsername(user.getUsername());

        if (userByUsername == null) {
            throw new BusinessException("Invalid username or password.");
        }

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userByUsername.getId(), user.getPassword())
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
    
        return ResponseHandler.generateResponse(new LoginSuccessResponse(token, userId, role, firstName, lastName));
    }
}