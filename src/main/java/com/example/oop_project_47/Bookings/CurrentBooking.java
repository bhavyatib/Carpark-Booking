package com.example.oop_project_47.Bookings;

import com.example.oop_project_47.Car_Owner.CarOwner;

public class CurrentBooking {
    static Booking currentBooking;

    static public Booking getCurrentBooking() {
        return currentBooking;
    }

    static public void setCurrentBooking(Booking current) {
        currentBooking = current;
    }
}
