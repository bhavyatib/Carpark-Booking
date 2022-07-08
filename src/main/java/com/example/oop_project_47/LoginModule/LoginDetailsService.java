package com.example.oop_project_47.LoginModule;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class LoginDetailsService implements UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<LoginCredentials> loginCredentials = loginRepository.findUserByUsername(username);
        loginCredentials.orElseThrow(()-> new UsernameNotFoundException("Not found:"+username));
        return loginCredentials.map(LoginCredentials::new).get();
    }
    public void createLogin(UserDetails user) {
        loginRepository.save((LoginCredentials) user);
    }
}