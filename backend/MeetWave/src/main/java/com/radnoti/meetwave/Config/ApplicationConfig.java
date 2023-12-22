package com.radnoti.meetwave.Config;

import com.radnoti.meetwave.Model.registerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final registerRepository repository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> repository.findByFullNameIN(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
