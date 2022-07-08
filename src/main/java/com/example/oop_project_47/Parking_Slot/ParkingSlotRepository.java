package com.example.oop_project_47.Parking_Slot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, String> {


    //List<ParkingSlot> findAll();

    Optional<ParkingSlot> findById(Integer id);

   // ParkingSlot findByDate(LocalDate date);
    void deleteById(Integer id);
    ParkingSlot save(ParkingSlot parkingSlot);
}
