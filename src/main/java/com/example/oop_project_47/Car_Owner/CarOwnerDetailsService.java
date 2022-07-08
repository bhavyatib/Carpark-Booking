package com.example.oop_project_47.Car_Owner;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class CarOwnerDetailsService implements UserDetailsService {
    @Autowired
    private CarOwnerRepository carOwnerRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<CarOwner> carOwner = carOwnerRepository.findByUsername(username);
        carOwner.orElseThrow(()-> new UsernameNotFoundException("Not found:"+username));
        return carOwner.map(CarOwner::new).get();
    }
    public void createCarOwner(UserDetails user) {
        carOwnerRepository.save((CarOwner) user);
    }
}