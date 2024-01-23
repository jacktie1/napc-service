package org.apathinternational.faithpathrestful.controller;

import org.apathinternational.faithpathrestful.security.DatabaseUserDetailsService;
import org.apathinternational.faithpathrestful.security.JwtTokenProvider;
import org.apathinternational.faithpathrestful.jsonmodel.LoginErrorResponse;
import org.apathinternational.faithpathrestful.jsonmodel.LoginSuccessResponse;
import org.apathinternational.faithpathrestful.model.User;
import org.apathinternational.faithpathrestful.repository.UserRepository;
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
    private UserRepository userRepository;

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
                .body(new LoginErrorResponse("Invalid credentials. Please check the username/password and try again."));
        } catch (DisabledException e) {
            // User disabled
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new LoginErrorResponse("User account is disabled. Please contact support for assistance."));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        String token = jwtTokenProvider.createToken(userDetails.getUsername());
    
        return ResponseEntity.ok(new LoginSuccessResponse(token));
    }

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody User request) {
        User encrypted_user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(encrypted_user);
    }
}