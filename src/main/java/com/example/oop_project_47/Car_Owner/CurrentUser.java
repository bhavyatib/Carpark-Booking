package com.example.oop_project_47.Car_Owner;

public class CurrentUser {
    static CarOwner currentUser;

    static public CarOwner getCurrentUser() {
        return currentUser;
    }

    static public void setCurrentUser(CarOwner current) {
        currentUser = current;
    }
}
