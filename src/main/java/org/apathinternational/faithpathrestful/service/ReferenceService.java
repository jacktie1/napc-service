package org.apathinternational.faithpathrestful.service;

import java.util.List;

import org.apathinternational.faithpathrestful.entity.Reference;
import org.apathinternational.faithpathrestful.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReferenceService {
    @Autowired
    private ReferenceRepository referenceRepository;

    public List<Reference> getReferenceByType(String referenceType) {
        return referenceRepository.findByReferenceType(referenceType);
    }

    public List<Reference> getReferenceByTypes(List<String> referenceTypes) {
        return referenceRepository.findByReferenceTypes(referenceTypes);
    }

    public List<Reference> getAllReferences() {
        return referenceRepository.findAll();
    }

    public Reference findById(Long id) {
        return referenceRepository.findById(id).orElse(null);
    }
}