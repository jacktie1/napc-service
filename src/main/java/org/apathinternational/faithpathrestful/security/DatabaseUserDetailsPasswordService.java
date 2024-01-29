package org.apathinternational.faithpathrestful.security;

import java.util.ArrayList;
import java.util.Collection;

import org.apathinternational.faithpathrestful.entity.Role;
import org.apathinternational.faithpathrestful.entity.User;
import org.apathinternational.faithpathrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
class DatabaseUserDetailsPasswordService implements UserDetailsPasswordService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String newPassword) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(userDetails.getUsername());

        if(user == null)
        {
            throw new UsernameNotFoundException("User not found with username: " + userDetails.getUsername());
        }

        user.setPassword(newPassword);
        
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                getAuthorities(user.getRole())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        // reutnr collection with 'ROLE_' + role name
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
        return authorities;
    }
}
