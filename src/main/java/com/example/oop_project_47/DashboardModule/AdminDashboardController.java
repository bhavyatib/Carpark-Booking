package com.example.oop_project_47.DashboardModule;


import com.example.oop_project_47.Admin.Admin;
import com.example.oop_project_47.Bookings.Booking;
import com.example.oop_project_47.Bookings.BookingRepository;
import com.example.oop_project_47.Car_Owner.CarOwner;
import com.example.oop_project_47.Car_Owner.CarOwnerRepository;
import com.example.oop_project_47.Car_Owner.ConfirmationToken;
import com.example.oop_project_47.LoginModule.LoginCredentials;
import com.example.oop_project_47.LoginModule.LoginRepository;
import com.example.oop_project_47.Parking_Space.ParkingSpace;
import com.example.oop_project_47.Parking_Space.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.mail.internet.AddressException;
import java.util.List;

@Controller
@RequestMapping("/Dashboard/a/")
public class AdminDashboardController implements WebMvcConfigurer {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private CarOwnerRepository carOwnerRepository;

    @GetMapping(value = "")
    public String displayHome(ModelMap modelMap, Booking booking) {

        return "/DashboardModule2/AdminDashboard/DashboardAdmin";
    }


    @GetMapping(value = "Bookings")
    public ModelAndView displayBooking(ModelAndView modelAndView, Booking booking) {

        List<Booking> allBookings = bookingRepository.findAll();
        if(allBookings.size() != 0)  {
            modelAndView.addObject("allBookings", allBookings);
    }
        else  {
            modelAndView.addObject("message", "No Bookings to Show!");

        }
        modelAndView.setViewName("/DashboardModule2/AdminDashboard/Bookings_admin");
        return modelAndView;
    }

    @RequestMapping(value = "ParkingSpace", method = RequestMethod.GET)
    public ModelAndView displayParkingSpace(ModelAndView modelAndView, ParkingSpace parkingSpace) {

        List<ParkingSpace> allParkingSpaces = parkingSpaceRepository.findAll();
        if(allParkingSpaces.size() != 0)  {
            modelAndView.addObject("allParkingSpaces", allParkingSpaces);
        }
        else  {
            modelAndView.addObject("message", "No Spaces to Show!");

        }

        modelAndView.setViewName("/DashboardModule2/AdminDashboard/ParkingSpacesAdmin");
        return modelAndView;
    }

    @RequestMapping(value = "ParkingSpace/Add", method = RequestMethod.GET)
    public ModelAndView displayParkingSpaceRegistration(ModelAndView modelAndView, ParkingSpace parkingSpace) {

        modelAndView.addObject("ParkingSpace", parkingSpace);
        modelAndView.setViewName("/DashboardModule2/AdminDashboard/AddParkingSpacesAdmin");
        return modelAndView;
    }

    @RequestMapping(value = "ParkingSpace/Add", method = RequestMethod.POST)
    public ModelAndView AddParkingSpace(ModelAndView modelAndView, ParkingSpace parkingSpace) {

        ParkingSpace existingSpace = parkingSpaceRepository.findByAddress(parkingSpace.getAddress());
        if (existingSpace != null) {
            modelAndView.addObject("message", "Parking Space with given address already exists");
            modelAndView.setViewName("/DashboardModule2/AdminDashboard/AddParkingSpacesAdmin");
            return modelAndView;
        } else {
            parkingSpace.setAvailableHatchback(parkingSpace.getHatchbackSlots());
            parkingSpace.setAvailableSedan(parkingSpace.getSedanSlots());
            parkingSpace.setAvailableSUV(parkingSpace.getSuvSlots());
            parkingSpace.setAvailableBuffer(parkingSpace.getBufferSlots());
            parkingSpaceRepository.save(parkingSpace);
            modelAndView.addObject("parkingSpace", parkingSpace);
            modelAndView.setViewName("redirect:/Dashboard/a/ParkingSpace");
            return modelAndView;
        }
    }


    @RequestMapping(value = "Amenities", method = RequestMethod.GET)
    public ModelAndView displayAmenities(ModelAndView modelAndView, LoginCredentials loginCredentials) {

        modelAndView.addObject("LoginCredentials", loginCredentials);
        modelAndView.setViewName("/DashboardModule2/AdminDashboard/AmenitiesAdmin");
        return modelAndView;
    }

    @RequestMapping(value = "Report", method = RequestMethod.GET)
    public ModelAndView displayReport(ModelAndView modelAndView, LoginCredentials loginCredentials) {

        modelAndView.addObject("LoginCredentials", loginCredentials);
        modelAndView.setViewName("/DashboardModule2/AdminDashboard/ReportAdmin");
        return modelAndView;
    }

    @RequestMapping(value = "AllUsers", method = RequestMethod.GET)
    public ModelAndView displayAllUsers(ModelAndView modelAndView, LoginCredentials loginCredentials) {

        List<LoginCredentials> allUserDetails = loginRepository.findAll();
        if(allUserDetails.size() != 0)  {
            modelAndView.addObject("allUserDetails", allUserDetails);
        }
        else  {
            modelAndView.addObject("message", "No Spaces to Show!");

        }
       // modelAndView.addObject("allUserDetails", allUserDetails);
        modelAndView.setViewName("/DashboardModule2/AdminDashboard/AllUsersAdmin");
        return modelAndView;
    }

    @RequestMapping(value = "Settings", method = RequestMethod.GET)
    public ModelAndView displaySetting(ModelAndView modelAndView, LoginCredentials loginCredentials) {

        modelAndView.addObject("LoginCredentials", loginCredentials);
        modelAndView.setViewName("/DashboardModule2/AdminDashboard/SettingAdmin");
        return modelAndView;
    }}







