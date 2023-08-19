package com.test.station.service.security;

import com.test.station.entity.MyCustomUserDetails;
import com.test.station.entity.User;
import com.test.station.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


public class UserDetailsServiceImplementation implements UserDetailsService {
    private static final String USER_NOT_FOUND_MSG = "User not found with Given username %s";
    private final UserRepository userRepository;

    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> mayBeUser = userRepository.findByUsername(username);
        User user = mayBeUser.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
        return new MyCustomUserDetails(user);
    }
}
