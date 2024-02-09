package org.apathinternational.faithpathrestful.controller;

import java.util.List;

import org.apathinternational.faithpathrestful.entity.Management;
import org.apathinternational.faithpathrestful.entity.Reference;
import org.apathinternational.faithpathrestful.model.request.GetReferencesRequest;
import org.apathinternational.faithpathrestful.model.response.GetManagementResponse;
import org.apathinternational.faithpathrestful.model.response.GetReferencesResponse;
import org.apathinternational.faithpathrestful.response.ResponseHandler;
import org.apathinternational.faithpathrestful.service.ManagementService;
import org.apathinternational.faithpathrestful.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private ReferenceService referenceService;

    @Autowired
    private ManagementService managementService;

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
    
}
