package com.hgrranzi.visaland.security;

import com.hgrranzi.visaland.persistence.entity.User;
import com.hgrranzi.visaland.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisalandUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Can't find user with username: " + username));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));


        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                                                                      user.getPassword(),
                                                                      authorities);
    }

}
