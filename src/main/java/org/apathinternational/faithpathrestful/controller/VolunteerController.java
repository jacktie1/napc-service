package org.apathinternational.faithpathrestful.controller;

import java.util.ArrayList;
import java.util.List;

import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.common.exception.CustomAccessDeniedException;
import org.apathinternational.faithpathrestful.entity.Reference;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.entity.UserSecurityQuestion;
import org.apathinternational.faithpathrestful.entity.Volunteer;
import org.apathinternational.faithpathrestful.model.entityDTO.UserAccountDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.VolunteerAirportPickupDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.VolunteerProfileDTO;
import org.apathinternational.faithpathrestful.model.entityDTO.VolunteerTempHousingDTO;
import org.apathinternational.faithpathrestful.model.request.RegisterVolunteerRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateVolunteerAirportPickupRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateVolunteerProfileRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateVolunteerTempHousingRequest;
import org.apathinternational.faithpathrestful.model.response.GetVolunteerResponse;
import org.apathinternational.faithpathrestful.model.response.MessageReponse;
import org.apathinternational.faithpathrestful.response.ResponseHandler;
import org.apathinternational.faithpathrestful.service.SessionService;
import org.apathinternational.faithpathrestful.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/volunteer")
@Validated
public class VolunteerController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private SessionService sessionService;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<?> register(@Valid @RequestBody RegisterVolunteerRequest request) {

        UserAccountDTO userAccount = request.getUserAccount();

        VolunteerProfileDTO volunteerProfile = request.getVolunteerProfile();

        VolunteerAirportPickupDTO volunteerFlightInfo = request.getVolunteerAirportPickup();

        VolunteerTempHousingDTO volunteerTempHousing = request.getVolunteerTempHousing();
        
        User encryptedUser = new User();
        encryptedUser.setUsername(userAccount.getUsername());
        encryptedUser.setEmailAddress(volunteerProfile.getEmailAddress());
        encryptedUser.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        encryptedUser.setFirstName(volunteerProfile.getFirstName());
        encryptedUser.setLastName(volunteerProfile.getLastName());
        encryptedUser.setGender(volunteerProfile.getGender());

        UserSecurityQuestion userSecurityQuestion1 = new UserSecurityQuestion();
        UserSecurityQuestion userSecurityQuestion2 = new UserSecurityQuestion();
        UserSecurityQuestion userSecurityQuestion3 = new UserSecurityQuestion();

        Reference securityQuestionReference1 = new Reference();
        Reference securityQuestionReference2 = new Reference();
        Reference securityQuestionReference3 = new Reference();

        securityQuestionReference1.setId(userAccount.getSecurityQuestionReferenceId1());
        securityQuestionReference2.setId(userAccount.getSecurityQuestionReferenceId2());
        securityQuestionReference3.setId(userAccount.getSecurityQuestionReferenceId3());
        userSecurityQuestion1.setSecurityQuestionReference(securityQuestionReference1);
        userSecurityQuestion1.setSecurityAnswer(passwordEncoder.encode(userAccount.getsecurityAnswer1().trim().toUpperCase()));
        userSecurityQuestion2.setSecurityQuestionReference(securityQuestionReference2);
        userSecurityQuestion2.setSecurityAnswer(passwordEncoder.encode(userAccount.getsecurityAnswer2().trim().toUpperCase()));
        userSecurityQuestion3.setSecurityQuestionReference(securityQuestionReference3);
        userSecurityQuestion3.setSecurityAnswer(passwordEncoder.encode(userAccount.getsecurityAnswer3().trim().toUpperCase()));

        List<UserSecurityQuestion> securityQuestions = new ArrayList<UserSecurityQuestion>();
        securityQuestions.add(userSecurityQuestion1);
        securityQuestions.add(userSecurityQuestion2);
        securityQuestions.add(userSecurityQuestion3);

        encryptedUser.setSecurityQuestions(securityQuestions);

        Volunteer volunteerUser = new Volunteer();
        volunteerUser.setUser(encryptedUser);

        volunteerUser.setAffiliation(volunteerProfile.getAffiliation());
        volunteerUser.setPrimaryPhoneNumber(volunteerProfile.getPrimaryPhoneNumber());
        volunteerUser.setSecondaryPhoneNumber(volunteerProfile.getSecondaryPhoneNumber());
        volunteerUser.setWechatId(volunteerProfile.getWechatId());
        volunteerUser.setProvidesAirportPickup(volunteerFlightInfo.getProvidesAirportPickup());
        volunteerUser.setCarManufacturer(volunteerFlightInfo.getCarManufacturer());
        volunteerUser.setCarModel(volunteerFlightInfo.getCarModel());
        volunteerUser.setNumCarSeats(volunteerFlightInfo.getNumCarSeats());
        volunteerUser.setNumMaxLgLuggages(volunteerFlightInfo.getNumMaxLgLuggages());
        volunteerUser.setNumMaxTrips(volunteerFlightInfo.getNumMaxTrips());
        volunteerUser.setAirportPickupComment(volunteerFlightInfo.getAirportPickupComment());
        volunteerUser.setProvidesTempHousing(volunteerTempHousing.getProvidesTempHousing());
        volunteerUser.setHomeAddress(volunteerTempHousing.getHomeAddress());
        volunteerUser.setNumMaxStudentsHosted(volunteerTempHousing.getNumMaxStudentsHosted());
        volunteerUser.setTempHousingStartDate(volunteerTempHousing.getTempHousingStartDate());
        volunteerUser.setTempHousingEndDate(volunteerTempHousing.getTempHousingEndDate());
        volunteerUser.setNumDoubleBeds(volunteerTempHousing.getNumDoubleBeds());
        volunteerUser.setNumSingleBeds(volunteerTempHousing.getNumSingleBeds());
        volunteerUser.setGenderPreference(volunteerTempHousing.getGenderPreference());
        volunteerUser.setProvidesRide(volunteerTempHousing.getProvidesRide());
        volunteerUser.setTempHousingComment(volunteerTempHousing.getTempHousingComment());

        volunteerService.createVolunteer(volunteerUser);

        return ResponseHandler.generateResponse(new MessageReponse("Volunteer User created successfully."));
    }

    @GetMapping("/getProfile/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_VOLUNTEER')")
    public ResponseEntity<?> getProfile(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isVolunteer() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to view this volunteer.");
        }

        Volunteer volunteer = volunteerService.getVolunteerByUserId(userId);

        if(volunteer == null) {
            throw new BusinessException("User is found but volunteer data is missing.");
        }

        GetVolunteerResponse response = new GetVolunteerResponse();

        response.setVolunteerProfile(volunteer);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getAirportPickup/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_VOLUNTEER')")
    public ResponseEntity<?> getAirportPickup(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isVolunteer() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to view this volunteer.");
        }

        Volunteer volunteer = volunteerService.getVolunteerByUserId(userId);

        if(volunteer == null) {
            throw new BusinessException("User is found but volunteer data is missing.");
        }

        GetVolunteerResponse response = new GetVolunteerResponse();

        response.setVolunteerAirportPickup(volunteer);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getTempHousing/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_VOLUNTEER')")
    public ResponseEntity<?> getTempHousing(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isVolunteer() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to view this volunteer.");
        }

        Volunteer volunteer = volunteerService.getVolunteerByUserId(userId);

        if(volunteer == null) {
            throw new BusinessException("User is found but volunteer data is missing.");
        }

        GetVolunteerResponse response = new GetVolunteerResponse();

        response.setVolunteerTempHousing(volunteer);

        return ResponseHandler.generateResponse(response);
    }

    @PutMapping("/updateProfile/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VOLUNTEER')")
    @Transactional
    public ResponseEntity<?> updateProfile(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateVolunteerProfileRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isVolunteer() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        VolunteerProfileDTO volunteerProfile = request.getVolunteerProfile();

        Volunteer volunteer = volunteerService.getVolunteerByUserId(userId);

        if(volunteer == null) {
            throw new BusinessException("User is found but volunteer data is missing.");
        }

        User volunteerUser = volunteer.getUser();

        // changes will be automatically saved to the database because of the @Transactional annotation
        volunteerUser.setFirstName(volunteerProfile.getFirstName());
        volunteerUser.setLastName(volunteerProfile.getLastName());
        volunteerUser.setGender(volunteerProfile.getGender());
        volunteerUser.setEmailAddress(volunteerProfile.getEmailAddress());

        volunteer.setAffiliation(volunteerProfile.getAffiliation());
        volunteer.setPrimaryPhoneNumber(volunteerProfile.getPrimaryPhoneNumber());
        volunteer.setSecondaryPhoneNumber(volunteerProfile.getSecondaryPhoneNumber());
        volunteer.setWechatId(volunteerProfile.getWechatId());

        return ResponseHandler.generateResponse(new MessageReponse("Profile updated successfully."));
    }

    @PutMapping("/updateAirportPickup/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VOLUNTEER')")
    @Transactional
    public ResponseEntity<?> updateAirportPickup(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateVolunteerAirportPickupRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isVolunteer() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        VolunteerAirportPickupDTO VolunteerAirportPickup = request.getVolunteerAirportPickup();

        Volunteer volunteer = volunteerService.getVolunteerByUserId(userId);

        if(volunteer == null) {
            throw new BusinessException("User is found but volunteer data is missing.");
        }

        // changes will be automatically saved to the database because of the @Transactional annotation
        volunteer.setProvidesAirportPickup(VolunteerAirportPickup.getProvidesAirportPickup());
        volunteer.setCarManufacturer(VolunteerAirportPickup.getCarManufacturer());
        volunteer.setCarModel(VolunteerAirportPickup.getCarModel());
        volunteer.setNumCarSeats(VolunteerAirportPickup.getNumCarSeats());
        volunteer.setNumMaxLgLuggages(VolunteerAirportPickup.getNumMaxLgLuggages());
        volunteer.setNumMaxTrips(VolunteerAirportPickup.getNumMaxTrips());
        volunteer.setAirportPickupComment(VolunteerAirportPickup.getAirportPickupComment());

        return ResponseHandler.generateResponse(new MessageReponse("AirportPickup updated successfully."));
    }

    @PutMapping("/updateTempHousing/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VOLUNTEER')")
    @Transactional
    public ResponseEntity<?> updateTempHousing(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateVolunteerTempHousingRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isVolunteer() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        VolunteerTempHousingDTO VolunteerTempHousing = request.getVolunteerTempHousing();

        Volunteer volunteer = volunteerService.getVolunteerByUserId(userId);

        if(volunteer == null) {
            throw new BusinessException("User is found but volunteer data is missing.");
        }

        // changes will be automatically saved to the database because of the @Transactional annotation
        volunteer.setProvidesTempHousing(VolunteerTempHousing.getProvidesTempHousing());
        volunteer.setHomeAddress(VolunteerTempHousing.getHomeAddress());
        volunteer.setNumMaxStudentsHosted(VolunteerTempHousing.getNumMaxStudentsHosted());
        volunteer.setTempHousingStartDate(VolunteerTempHousing.getTempHousingStartDate());
        volunteer.setTempHousingEndDate(VolunteerTempHousing.getTempHousingEndDate());
        volunteer.setNumDoubleBeds(VolunteerTempHousing.getNumDoubleBeds());
        volunteer.setNumSingleBeds(VolunteerTempHousing.getNumSingleBeds());
        volunteer.setGenderPreference(VolunteerTempHousing.getGenderPreference());
        volunteer.setProvidesRide(VolunteerTempHousing.getProvidesRide());
        volunteer.setTempHousingComment(VolunteerTempHousing.getTempHousingComment());

        return ResponseHandler.generateResponse(new MessageReponse("TempHousing updated successfully."));
    }


}