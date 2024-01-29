package org.apathinternational.faithpathrestful.controller;

import org.apathinternational.faithpathrestful.security.DatabaseUserDetailsService;
import org.apathinternational.faithpathrestful.security.JwtTokenProvider;
import org.apathinternational.faithpathrestful.service.RoleService;
import org.apathinternational.faithpathrestful.service.UserService;
import org.apathinternational.faithpathrestful.model.request.SignupRequest;
import org.apathinternational.faithpathrestful.model.response.LoginSuccessResponse;
import org.apathinternational.faithpathrestful.model.response.SignupSuccessReponse;
import org.apathinternational.faithpathrestful.entity.Role;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.common.exception.ValidationException;
import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.response.ResponseHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private DatabaseUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BusinessException("Invalid username or password.");
        } catch (DisabledException e) {
            // User disabled
            throw new BusinessException("User account is disabled. Please contact support for assistance.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        String token = jwtTokenProvider.createToken(userDetails.getUsername());

        User authedUser = userService.getUserByUsername(userDetails.getUsername());

        String role = authedUser.getRole().getName();
    
        return ResponseHandler.generateResponse(new LoginSuccessResponse(token, role));
    }

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody SignupRequest request) {
        User encrypted_user = new User();
        encrypted_user.setUsername(request.getUsername());
        encrypted_user.setEmailAddress(request.getEmailAddress());
        encrypted_user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleService.getRoleByName(request.getRole());

        if(role == null)
        {
            throw new ValidationException("Invalid role. Please check the role and try again.");
        }

        encrypted_user.setRole(role);
        encrypted_user.setEnabled(true);
        userService.createUser(encrypted_user);

        return ResponseHandler.generateResponse(new SignupSuccessReponse("User created successfully."));
    }
}