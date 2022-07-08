package com.example.oop_project_47.Admin;

import com.example.oop_project_47.Model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Admin")
public class Admin extends User {
    @Column(name = "ID")
    @NotEmpty
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

    @Override
    public Integer getId()  {return 47;}

    @Override
    public String getFirstName()  {return "Admin";}

    @Override
    public String getLastName()  {return " ";}

    @Override
    public String getUsername()  {return "admin";}

    @Override
    public String getPassword()  {return "admin";}

    @Override
    public String getEmailId()  {return "bookit4747@gmail.com";}

    @Override
    public String getPhoneNumber()  {return "+18507897596";}




}
