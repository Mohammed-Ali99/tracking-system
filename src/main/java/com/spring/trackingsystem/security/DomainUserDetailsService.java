package com.spring.trackingsystem.security;

import com.spring.trackingsystem.entity.enums.Role;
import com.spring.trackingsystem.entity.User;
import com.spring.trackingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return createSpringSecurityUser(user);
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        Role role = user.getRole();
        String roleName = "ROLE_" + role.name();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
        grantedAuthorities.add(simpleGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
