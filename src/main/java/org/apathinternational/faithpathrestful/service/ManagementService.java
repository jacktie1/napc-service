package org.apathinternational.faithpathrestful.service;

import java.util.Optional;
import java.util.List;

import org.apathinternational.faithpathrestful.entity.Management;
import org.apathinternational.faithpathrestful.repository.ManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementService {

    @Autowired
    private ManagementRepository ManagementRepository;

    public Management getManagement() {
        List<Management> managements = ManagementRepository.findAll();

        if(managements.isEmpty()) {
            return null;
        }

        Optional<Management> optionalManagement = managements.stream().findFirst();
        return optionalManagement.get();
    }
    
}
