package org.apathinternational.faithpathrestful.controller;

import org.apathinternational.faithpathrestful.security.DatabaseUserDetailsService;
import org.apathinternational.faithpathrestful.security.JwtTokenProvider;
import org.apathinternational.faithpathrestful.service.RoleService;
import org.apathinternational.faithpathrestful.service.UserService;
import org.apathinternational.faithpathrestful.jsonmodel.ErrorResponse;
import org.apathinternational.faithpathrestful.jsonmodel.LoginSuccessResponse;
import org.apathinternational.faithpathrestful.jsonmodel.SignupRequest;
import org.apathinternational.faithpathrestful.jsonmodel.SignupSuccessReponse;
import org.apathinternational.faithpathrestful.model.Role;
import org.apathinternational.faithpathrestful.model.User;
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
            // Invalid password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("Invalid credentials. Please check the username/password and try again."));
        } catch (DisabledException e) {
            // User disabled
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse("User account is disabled. Please contact support for assistance."));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        String token = jwtTokenProvider.createToken(userDetails.getUsername());
    
        return ResponseEntity.ok(new LoginSuccessResponse(token));
    }

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody SignupRequest request) {
        User encrypted_user = new User();
        encrypted_user.setUsername(request.getUsername());
        encrypted_user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleService.getRoleByName(request.getRole());

        if(role == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Invalid role. Please check the role and try again."));
        }

        encrypted_user.setRole(role);
        encrypted_user.setEnabled(true);
        userService.createUser(encrypted_user);

        return ResponseEntity.ok(new SignupSuccessReponse("User created successfully."));
    }
}