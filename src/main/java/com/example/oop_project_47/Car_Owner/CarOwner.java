package com.example.oop_project_47.Car_Owner;
import java.util.*;

import com.example.oop_project_47.Model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "Car_Owners")
public class CarOwner extends User implements UserDetails {

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

    @Column(name = "Car_Type")
    @NotEmpty
    private String carType;

    @Column(name = "Address")
    @NotEmpty
    private String address;

    @Column(name = "Car_Registration_Number")
    @NotEmpty
    private String carRegistrationNumber;

    @Column(name = "Wallet")
    private Long wallet;

    public Long getWallet() {
        return wallet;
    }

    public void setWallet(Long wallet) {
        this.wallet = wallet;
    }

    public AuthenticationProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider")
    private AuthenticationProvider authProvider;

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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;


    }

    public String getCarRegistrationNumber() {
        return this.carRegistrationNumber;
    }

    public void setCarRegistrationNumber(String carRegistrationNumber) {
        this.carRegistrationNumber = carRegistrationNumber;


    }


    public CarOwner() {
    }

    public CarOwner(User user) {
        this.username = user.getUsername();
        this.emailId = user.getEmailId();
        this.password = user.getPassword();
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();

    }

    public void fillCarOwner() {
        this.username = super.getUsername();
        this.emailId = super.getEmailId();
        this.password = super.getPassword();
        this.firstName = super.getFirstName();
        this.lastName = super.getLastName();
        this.phoneNumber = super.getPhoneNumber();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
