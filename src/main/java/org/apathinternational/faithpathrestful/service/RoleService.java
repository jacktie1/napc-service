package org.apathinternational.faithpathrestful.service;

import java.util.Optional;

import org.apathinternational.faithpathrestful.model.Role;
import org.apathinternational.faithpathrestful.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByName(String name) {
        Optional<Role> optionalRole = roleRepository.findByName(name);
        return optionalRole.orElse(null);
    }
    
}
