package com.test.station.security;

import com.test.station.repository.UserRepository;
import com.test.station.service.security.UserDetailsServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserDetailServiceConfiguration {
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImplementation(userRepository);
    }
}
