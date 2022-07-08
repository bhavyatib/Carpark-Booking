package com.example.oop_project_47.Worker;

import com.example.oop_project_47.Model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Car_Owners")
public class Worker extends User {
    @Column(name = "ID")
    private Integer id;

    @Column(name = "First_Name")
    @NotEmpty
    private String firstName;

    @Column(name = "Last_Name")
    @NotEmpty
    private String lastName;

    @Column(name = "Username")
    @NotEmpty
    private String username;

    @Column(name = "Password")
    @NotEmpty
    private String password;

    @Column(name = "Email_ID")
    @NotEmpty
    private String emailId;

    @Column(name = "Phone_Number")
    @NotEmpty
    private String phoneNumber;

    @Column(name = "Address")
    @NotEmpty
    private String address;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmailId() {
        return emailId;
    }

    @Override
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
