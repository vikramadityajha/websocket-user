package com.example.springwebsockets.springsecuredsockets.transfer.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {

    private com.example.springwebsockets.springsecuredsockets.domain.User user;

    public CustomUserDetails(com.example.springwebsockets.springsecuredsockets.domain.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    public CustomUserDetails(com.example.springwebsockets.springsecuredsockets.domain.User user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    public com.example.springwebsockets.springsecuredsockets.domain.User getUser() {
        return user;
    }
}
