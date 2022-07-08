package com.example.oop_project_47.DashboardModule;

import com.example.oop_project_47.Admin.Admin;
import com.example.oop_project_47.Bookings.Booking;
import com.example.oop_project_47.Bookings.CurrentBooking;
import com.example.oop_project_47.Car_Owner.CarOwner;
import com.example.oop_project_47.Car_Owner.CarOwnerRepository;
import com.example.oop_project_47.Car_Owner.CurrentUser;
import com.example.oop_project_47.LoginModule.LoginController;
import com.example.oop_project_47.LoginModule.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
@RequestMapping("/Dashboard/c/")
public class CarOwnerDashboardController implements WebMvcConfigurer {

    @Autowired
    private CarOwnerRepository carOwnerRepository;

    LoginController loginController = new LoginController();

    Admin admin = new Admin();

    int carOwnerId = loginController.getCarOwnerId();
    final int userId = carOwnerId;

    @GetMapping(value = "")
    public ModelAndView displayHome(ModelAndView modelAndView, CarOwner carOwner) {
        carOwner = CurrentUser.getCurrentUser();
        modelAndView.addObject("carOwner", carOwner);
        modelAndView.setViewName("/DashboardModule2/UserDashboard/HomeUserDashboard");
        return modelAndView;
    }

    @GetMapping(value = "BookingSlot")
    public ModelAndView displayBookingSlot(ModelAndView modelAndView, Booking booking) {
        modelAndView.addObject("booking", booking);
        modelAndView.setViewName("/DashboardModule2/UserDashboard/BookingSlotUserDashboard");
        return modelAndView;
    }

    @GetMapping(value = "AllBookings")
    public ModelAndView displayAllBookings(ModelAndView modelAndView, CarOwner carOwner) {
        modelAndView.addObject("carOwner", carOwner);
        modelAndView.setViewName("/DashboardModule2/UserDashboard/AllBookingUserDashboard");
        return modelAndView;
    }

    @GetMapping(value = "Wallet")
    public ModelAndView displayWallet(ModelAndView modelAndView, CarOwner currentUser) {
        currentUser = CurrentUser.getCurrentUser();
        modelAndView.addObject("wallet", currentUser.getWallet());
        modelAndView.addObject("currentUser", currentUser);
        modelAndView.setViewName("/DashboardModule2/UserDashboard/WalletUserDashboard");
        return modelAndView;
    }

    @PostMapping(value = "Wallet/Add")
    public ModelAndView CalculateWallet(ModelAndView modelAndView, CarOwner currentUser) {
        CarOwner finalUser = CurrentUser.getCurrentUser();
        if(currentUser.getWallet() <=0)
            ;
        else {
        finalUser.setWallet(currentUser.getWallet()+ finalUser.getWallet());
        CurrentUser.setCurrentUser(finalUser);
        //modelAndView.addObject("wallet", finalUser.getWallet());
        carOwnerRepository.save(finalUser); }
        modelAndView.setViewName("redirect:/Dashboard/c/Wallet");
        return modelAndView;
    }

    @GetMapping(value = "Setting")
    public ModelAndView displaySetting(ModelAndView modelAndView, CarOwner carOwner) {
        modelAndView.addObject("carOwner", carOwner);
        modelAndView.setViewName("/DashboardModule2/UserDashboard/SettingUserDashboard");
        return modelAndView;
    }
}
