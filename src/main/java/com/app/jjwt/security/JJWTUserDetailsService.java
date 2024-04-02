package com.app.jjwt.security;

import com.app.jjwt.entities.JJWTUser;
import com.app.jjwt.entities.Role;
import com.app.jjwt.repositories.JJWTUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JJWTUserDetailsService implements UserDetailsService {

    private JJWTUserRepository jjwtUserRepository;

    @Autowired
    public JJWTUserDetailsService(JJWTUserRepository jjwtUserRepository){
        this.jjwtUserRepository = jjwtUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JJWTUser jjwtUser = this.jjwtUserRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return new User(jjwtUser.getLogin(), jjwtUser.getPassword(), this.mapRolesToAuthorities(jjwtUser.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
}
