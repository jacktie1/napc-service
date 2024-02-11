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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // The 'username' of UserDetails instance is actually the user id because username can change
        User user = userService.getUserById(Long.parseLong(username));

        if(user == null)
        {
            throw new UsernameNotFoundException("User not found with id: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getId().toString(),
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