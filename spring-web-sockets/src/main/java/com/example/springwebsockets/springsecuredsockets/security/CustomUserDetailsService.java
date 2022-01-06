package com.example.springwebsockets.springsecuredsockets.security;

import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springwebsockets.springsecuredsockets.domain.Role;
import com.example.springwebsockets.springsecuredsockets.domain.User;
import com.example.springwebsockets.springsecuredsockets.repository.UserRepository;
import com.example.springwebsockets.springsecuredsockets.transfer.user.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    UserRepository userRepository;

    public CustomUserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            if (user != null) return new CustomUserDetails(user, getAuthorities(user));
        } catch (Exception ex) {
            log.error("Exception in CustomUserDetailsService: " + ex);
        }
        return null;
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}
