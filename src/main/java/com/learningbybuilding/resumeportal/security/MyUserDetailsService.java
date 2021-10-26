package com.learningbybuilding.resumeportal.security;

import com.learningbybuilding.resumeportal.security.models.MyUserDetails;
import com.learningbybuilding.resumeportal.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private static final String MESSAGE = "User with name as %s does not exist";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .map(user -> new MyUserDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException(String.format(MESSAGE, username)));
    }
}
