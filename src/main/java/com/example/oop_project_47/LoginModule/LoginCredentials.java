package com.example.oop_project_47.LoginModule;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Table(name = "Login_Credentials")
public class LoginCredentials implements UserDetails {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "User_Role")
    @NotEmpty
    private String user_role;

    @Column(name = "Username")
    @NotEmpty
    private String username;

    @Column(name = "Password")
    @NotEmpty
    private String password;

    @Column(name = "Phone_Number")
    @NotEmpty
    private String phoneNumber;

    @Column(name = "Email_ID")
    @NotEmpty
    private String email_id;

    public void setAdminCredentials(Integer id, String user_role, String username, String password, String email_id, String phoneNumber) {
        this.id = id;
        this.user_role = user_role;
        this.username = username;
        this.password = password;
        this.email_id = email_id;
        this.phoneNumber = phoneNumber;
    }
    public LoginCredentials(Integer id, String user_role, String username, String password, String email_id, String phoneNumber) {
        this.id = id;
        this.user_role = user_role;
        this.username = username;
        this.password = password;
        this.email_id = email_id;
        this.phoneNumber = phoneNumber;

    }

    public LoginCredentials() {

    }

    public LoginCredentials(LoginCredentials loginCredentials) {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
}
