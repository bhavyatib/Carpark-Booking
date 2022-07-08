package com.example.oop_project_47.Parking_Space;

import com.example.oop_project_47.Model.NamedEntity;
import com.example.oop_project_47.Worker.Worker;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "Parking_Spaces")
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Location")
    //@NotEmpty
    private String location;

    @Column(name = "Address")
    //@NotEmpty
    private String address;

    @Column(name = "Hatchback_Slots")
    //@NotEmpty
    private Integer hatchbackSlots;

    @Column(name = "Sedan_Slots")
    //@NotEmpty
    private Integer sedanSlots;

    @Column(name = "SUV_Slots")
    //@NotEmpty
    private Integer suvSlots;

    @Column(name = "Buffer_Slots")
    //@NotEmpty
    private Integer bufferSlots;

    @Column(name = "Number_of_Workers")
    //@NotEmpty
    private Integer workerCount;

    @Column(name = "Hatchback_Status")
    //@NotEmpty
    private String hatchbackStatus;

    @Column(name = "Hatchback_Slots_Available")
    //@NotEmpty
    private Integer availableHatchback;

    @Column(name = "Sedan_Status")
    //@NotEmpty
    private String sedanStatus;

    @Column(name = "Sedan_Slots_Available")
    //@NotEmpty
    private Integer availableSedan;

    @Column(name = "SUV_Status")
    //@NotEmpty
    private String SUVStatus;

    @Column(name = "SUV_Slots_Available")
    //@NotEmpty
    private Integer availableSUV;

    @Column(name = "Buffer_Slots_Available")
    //@NotEmpty
    private Integer availableBuffer;


    @Column(name = "Status")
    //@NotEmpty
    private String status;

    @Column(name = "Worker_Rating")
    //@NotEmpty
    private Integer workerRating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getHatchbackSlots() {
        return hatchbackSlots;
    }

    public void setHatchbackSlots(Integer hatchbackSlots) {
        this.hatchbackSlots = hatchbackSlots;
    }

    public Integer getSedanSlots() {
        return sedanSlots;
    }

    public void setSedanSlots(Integer sedanSlots) {
        this.sedanSlots = sedanSlots;
    }

    public Integer getSuvSlots() {
        return suvSlots;
    }

    public void setSuvSlots(Integer suvSlots) {
        this.suvSlots = suvSlots;
    }

    public Integer getBufferSlots() {
        return bufferSlots;
    }

    public void setBufferSlots(Integer bufferSlots) {
        this.bufferSlots = bufferSlots;
    }

    public Integer getAvailableHatchback() {
        return availableHatchback;
    }

    public void setAvailableHatchback(Integer availableHatchback) {
        this.availableHatchback = availableHatchback;
    }

    public Integer getAvailableSedan() {
        return availableSedan;
    }

    public void setAvailableSedan(Integer availableSedan) {
        this.availableSedan = availableSedan;
    }

    public Integer getAvailableSUV() {
        return availableSUV;
    }

    public void setAvailableSUV(Integer availableSUV) {
        this.availableSUV = availableSUV;
    }

    public Integer getAvailableBuffer() {
        return availableBuffer;
    }

    public void setAvailableBuffer(Integer availableBuffer) {
        this.availableBuffer = availableBuffer;
    }

    public Integer getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(Integer workerCount) {
        this.workerCount = workerCount;
    }

    public String getHatchbackStatus() {
        return hatchbackStatus;
    }

    public void setHatchbackStatus(String hatchbackStatus) {
        this.hatchbackStatus = hatchbackStatus;
    }

    public String getSedanStatus() {
        return sedanStatus;
    }

    public void setSedanStatus(String sedanStatus) {
        this.sedanStatus = sedanStatus;
    }

    public String getSUVStatus() {
        return SUVStatus;
    }

    public void setSUVStatus(String SUVStatus) {
        this.SUVStatus = SUVStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getWorkerRating() {
        return workerRating;
    }

    public void setWorkerRating(Integer workerRating) {
        this.workerRating = workerRating;
    }
}
