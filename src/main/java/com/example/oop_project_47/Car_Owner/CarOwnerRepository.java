package com.example.oop_project_47.Car_Owner;


import java.util.List;
import java.util.Optional;

import com.example.oop_project_47.Bookings.Booking;
import org.springframework.data.repository.Repository;

public interface CarOwnerRepository extends Repository<CarOwner, Integer> {
    CarOwner findUserByUsername(String username);
    Optional<CarOwner> findByUsername(String username);

    List<CarOwner> findAll();
    CarOwner findUserByEmailId(String email_id);
    CarOwner findUserByPhoneNumber(String phoneNumber);

    List<CarOwner> findUserById(Integer userId);

    CarOwner findById(Integer categoryId);

    CarOwner save(CarOwner carOwner);

    Long deleteById(Integer categoryId);

    void delete(CarOwner carOwner);
}
