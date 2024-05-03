package org.apathinternational.faithpathrestful.controller;

import java.util.List;

import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.common.exception.CustomAccessDeniedException;
import org.apathinternational.faithpathrestful.entity.Management;
import org.apathinternational.faithpathrestful.entity.Reference;
import org.apathinternational.faithpathrestful.entity.Administrator;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.model.entityDTO.AdminProfileDTO;
import org.apathinternational.faithpathrestful.model.request.GetReferencesRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateManagementRequest;
import org.apathinternational.faithpathrestful.model.request.UpdateAdminProfileRequest;
import org.apathinternational.faithpathrestful.model.response.GetManagementResponse;
import org.apathinternational.faithpathrestful.model.response.GetReferencesResponse;
import org.apathinternational.faithpathrestful.model.response.GetAdminResponse;
import org.apathinternational.faithpathrestful.model.response.MessageReponse;
import org.apathinternational.faithpathrestful.response.ResponseHandler;
import org.apathinternational.faithpathrestful.service.AdminService;
import org.apathinternational.faithpathrestful.service.ManagementService;
import org.apathinternational.faithpathrestful.service.ReferenceService;
import org.apathinternational.faithpathrestful.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private ReferenceService referenceService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/getReferences")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> getReferences(@ModelAttribute GetReferencesRequest request) {
        List<Reference> references;
    
        if(request.getReferenceTypes() == null) {
            references = referenceService.getAllReferences();
        }
        else {
            references = referenceService.getReferenceByTypes(request.getReferenceTypes());
        }
        
        GetReferencesResponse response = new GetReferencesResponse(references);

        return ResponseHandler.generateResponse(response);
    }

    @GetMapping("/getManagement")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> getManagement() {
        Management management = managementService.getManagement();
        GetManagementResponse response = new GetManagementResponse(management);
        return ResponseHandler.generateResponse(response);
    }

    @PutMapping("/updateManagement")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.OK)
    @Transactional
    public ResponseEntity<?> updateManagement(@Valid @RequestBody UpdateManagementRequest request) {
        Management management = managementService.getManagement();

        management.setDoesAssignmentStart(request.getManagement().getDoesAssignmentStart());
        management.setStudentRegistrationStartDate(request.getManagement().getStudentRegistrationStartDate());
        management.setStudentRegistrationEndDate(request.getManagement().getStudentRegistrationEndDate());
        management.setAnnouncement(request.getManagement().getAnnouncement());
        management.setWeekOfWelcomeStartDate(request.getManagement().getWeekOfWelcomeStartDate());

        return ResponseHandler.generateResponse(new MessageReponse("Management updated successfully."));
    }

    @GetMapping("/getProfile/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getProfile(@PathVariable(required=true, name="userId") Long userId) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isAdmin() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to view this admin.");
        }

        Administrator admin = adminService.getAdminByUserId(userId);

        if(admin == null) {
            throw new BusinessException("User is found but admin data is missing.");
        }

        GetAdminResponse response = new GetAdminResponse();

        response.setAdminProfile(admin);

        return ResponseHandler.generateResponse(response);
    }

    @PutMapping("/updateProfile/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public ResponseEntity<?> updateProfile(@PathVariable(required=true, name="userId") Long userId, @Valid @RequestBody UpdateAdminProfileRequest request) {
        User authedUser = sessionService.getAuthedUser();

        if(authedUser.isAdmin() && !authedUser.getId().equals(userId)) {
            throw new CustomAccessDeniedException("You are not authorized to modify this resource.");
        }

        AdminProfileDTO adminProfile = request.getAdminProfile();

        Administrator admin = adminService.getAdminByUserId(userId);

        if(admin == null) {
            throw new BusinessException("User is found but admin data is missing.");
        }

        User adminUser = admin.getUser();

        // changes will be automatically saved to the database because of the @Transactional annotation
        adminUser.setFirstName(adminProfile.getFirstName());
        adminUser.setLastName(adminProfile.getLastName());
        adminUser.setGender(adminProfile.getGender());
        adminUser.setEmailAddress(adminProfile.getEmailAddress());

        admin.setAffiliation(adminProfile.getAffiliation());
        admin.setPrimaryPhoneNumber(adminProfile.getPrimaryPhoneNumber());

        return ResponseHandler.generateResponse(new MessageReponse("Profile updated successfully."));
    }
    
}
