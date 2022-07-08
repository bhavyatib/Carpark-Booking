package com.example.oop_project_47.Bookings;

import com.example.oop_project_47.Car_Owner.CarOwner;
import com.example.oop_project_47.Car_Owner.ConfirmationToken;
import com.example.oop_project_47.Car_Owner.CurrentUser;
import com.example.oop_project_47.Checkout_Reminder;
import com.example.oop_project_47.LoginModule.LoginController;
import com.example.oop_project_47.LoginModule.LoginCredentials;
import com.example.oop_project_47.Parking_Space.ParkingSpace;
import com.example.oop_project_47.Parking_Space.ParkingSpaceRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
public class BookingController {
    LoginController loginController;
    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;
    @Autowired
    private BookingRepository bookingRepository;
    String location;

    @RequestMapping(value = "/Dashboard/c/BookingSlot/SpaceInformation", method = RequestMethod.POST)
    public ModelAndView searchSlots(ModelAndView modelAndView, Booking booking) {
        // carOwner.fillCarOwner();
        location = booking.getLocation();
        List<ParkingSpace> nearBySpaces = parkingSpaceRepository.findAllByLocation(location);
        List<Booking> allSpaceBookings;
        CarOwner currentOwner = CurrentUser.getCurrentUser();
        if (currentOwner.getCarType() == null) {
            modelAndView.addObject("currentUser", currentOwner);
            modelAndView.setViewName("/DashboardModule2/UserDashboard/PreRegFormUserDashboard");
            return modelAndView;
        } else { // ParkingSpace parkingSpace = new ParkingSpace();
            if (nearBySpaces.size() == 0) {
                modelAndView.addObject("message", "No Parking Spaces Available for the given Location");
                modelAndView.setViewName("redirect:/Dashboard/c/BookingSlot");
                return modelAndView;
            } else {
                int clicked = 0;
                List<String> relevantSlots = new ArrayList<String>();
                for (int i = 0; i < nearBySpaces.size(); i++) {
                    nearBySpaces.get(i).setStatus("Not Available");
                    //nearBySpaces.get(i).setAvailableHatchback(nearBySpaces.get(i).getHatchbackSlots());
                    //nearBySpaces.get(i).setAvailableSedan(nearBySpaces.get(i).getSedanSlots());
                    //nearBySpaces.get(i).setAvailableSUV(nearBySpaces.get(i).getSuvSlots());
                    //nearBySpaces.get(i).setAvailableBuffer(nearBySpaces.get(i).getBufferSlots());
                    String carType = currentOwner.getCarType();
                    booking.setCarType(carType);
                    if (currentOwner.getCarType().equals("Hatchback")) {
                        if (nearBySpaces.get(i).getAvailableHatchback() > 0) {
                            nearBySpaces.get(i).setStatus("Available");
                            nearBySpaces.get(i).setHatchbackStatus("Book");
                            if(i==0) booking.setSlotType("Hatchback");
                            // nearBySpaces.get(i).setAvailableHatchback(nearBySpaces.get(i).getAvailableHatchback() - 1);
                        } else if (nearBySpaces.get(i).getAvailableSedan() > 0) {
                            nearBySpaces.get(i).setStatus("Available");
                            nearBySpaces.get(i).setSedanStatus("Book");
                            if(i==0) booking.setSlotType("Sedan");
                            // nearBySpaces.get(i).setAvailableSedan(nearBySpaces.get(i).getAvailableSedan() - 1);
                        } else if (nearBySpaces.get(i).getAvailableSUV() > 0) {
                            nearBySpaces.get(i).setStatus("Available");
                            nearBySpaces.get(i).setSUVStatus("Book");
                            if(i==0) booking.setSlotType("SUV");
                            //nearBySpaces.get(i).setAvailableSUV(nearBySpaces.get(i).getAvailableSUV() - 1);
                        } else {
                            relevantSlots.add("Hatchback");
                            relevantSlots.add("Sedan");
                            relevantSlots.add("SUV");
                            allSpaceBookings = bookingRepository.findAllBySpaceAndCarTypeIn(nearBySpaces.get(i).getId(), relevantSlots);
                            if(allSpaceBookings.size() == 0)  {

                            } else {
                            LocalDateTime earliestAvailable = Collections.min(allSpaceBookings.stream().map(Booking::getCheckOut).collect(Collectors.toList()));
                            long diffInMilli = Math.abs(earliestAvailable.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()) - LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                            long diff = TimeUnit.HOURS.convert(diffInMilli, TimeUnit.MILLISECONDS);
                            nearBySpaces.get(i).setStatus("Available in" + diff);
                            if (clicked == nearBySpaces.get(i).getId()) {
                                modelAndView.addObject("message", "This parking Space is not available for Booking!");
                                modelAndView.setViewName("/DashboardModule2/UserDashboard/BookingSlotUserDashboard");
                                return modelAndView;
                            }
                            }
                        }
                    } else if (currentOwner.getCarType().equals("Sedan")) {

                        if (nearBySpaces.get(i).getAvailableSedan() > 0) {
                            nearBySpaces.get(i).setStatus("Available");
                            nearBySpaces.get(i).setSedanStatus("Book");
                            if(i==0) booking.setSlotType("Sedan");
                            //nearBySpaces.get(i).setAvailableSedan(nearBySpaces.get(i).getAvailableSedan() - 1);
                        } else if (nearBySpaces.get(i).getAvailableSUV() > 0) {
                            nearBySpaces.get(i).setStatus("Available");
                            nearBySpaces.get(i).setSUVStatus("Book");
                            if(i==0) booking.setSlotType("SUV");
                            //nearBySpaces.get(i).setAvailableSUV(nearBySpaces.get(i).getAvailableSUV() - 1);
                        } else {
                            relevantSlots.add("Sedan");
                            relevantSlots.add("SUV");
                            allSpaceBookings = bookingRepository.findAllBySpaceAndCarTypeIn(nearBySpaces.get(i).getId(), relevantSlots);
                            if(allSpaceBookings.size() == 0)  {

                            } else {
                                LocalDateTime earliestAvailable = Collections.min(allSpaceBookings.stream().map(Booking::getCheckOut).collect(Collectors.toList()));
                                long diffInMilli = Math.abs(earliestAvailable.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()) - LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                                long diff = TimeUnit.HOURS.convert(diffInMilli, TimeUnit.MILLISECONDS);
                                nearBySpaces.get(i).setStatus("Available in" + diff);
                                if (clicked == nearBySpaces.get(i).getId()) {
                                    modelAndView.addObject("message", "This parking Space is not available for Booking!");
                                    modelAndView.setViewName("/DashboardModule2/UserDashboard/BookingSlotUserDashboard");
                                    return modelAndView;
                                }
                            }
                        }

                    } else if (currentOwner.getCarType().equals("SUV")) {


                        if (nearBySpaces.get(i).getAvailableSUV() > 0) {
                            nearBySpaces.get(i).setStatus("Available");
                            nearBySpaces.get(i).setSUVStatus("Book");
                            if(i==0) booking.setSlotType("SUV");
                            //nearBySpaces.get(i).setAvailableSUV(nearBySpaces.get(i).getAvailableSUV() - 1);
                        }  else {
                            relevantSlots.add("SUV");
                            allSpaceBookings = bookingRepository.findAllBySpaceAndCarTypeIn(nearBySpaces.get(i).getId(), relevantSlots);
                            if(allSpaceBookings.size() == 0)  {

                            } else {
                                LocalDateTime earliestAvailable = Collections.min(allSpaceBookings.stream().map(Booking::getCheckOut).collect(Collectors.toList()));
                                long diffInMilli = Math.abs(earliestAvailable.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()) - LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                                long diff = TimeUnit.HOURS.convert(diffInMilli, TimeUnit.MILLISECONDS);
                                nearBySpaces.get(i).setStatus("Available in" + diff);
                                if (clicked == nearBySpaces.get(i).getId()) {
                                    modelAndView.addObject("message", "This parking Space is not available for Booking!");
                                    modelAndView.setViewName("/DashboardModule2/UserDashboard/BookingSlotUserDashboard");
                                    return modelAndView;
                                }

                            }
                        }
                    }
                    parkingSpaceRepository.save(nearBySpaces.get(i));
                }
            }
            modelAndView.addObject("parkingSpace", new ParkingSpace());
            modelAndView.addObject("nearBySpaces", nearBySpaces);
            CurrentBooking.setCurrentBooking(booking);
            modelAndView.setViewName("/DashboardModule2/UserDashboard/BookingSlotScreen1UserDashboard");
            return modelAndView;
        }

    }


    @RequestMapping(value = "/Dashboard/c/BookingSlot/Checkout", method = RequestMethod.POST)
    public ModelAndView CheckoutOrFillDetails(ModelAndView modelAndView, @RequestParam("group1") int count) {
        // carOwner.fillCarOwner();
        // ParkingSpace parkingSpace = new ParkingSpace();
        List<ParkingSpace> nearBySpaces = parkingSpaceRepository.findAllByLocation(location);
        modelAndView.addObject("nearBySpaces", nearBySpaces);
        modelAndView.addObject("count", count);
        //modelAndView.addObject("parkingSpace", parkingSpace);
         ParkingSpace bookParkingSpace = parkingSpaceRepository.findById(count);
        if (!(bookParkingSpace.getStatus().equals("Available"))) {
            modelAndView.addObject("message", "Not Available for booking");
            modelAndView.setViewName("/DashboardModule2/UserDashboard/BookingSlotScreen1UserDashboard");}
         else if (bookParkingSpace == null) {
            modelAndView.addObject("message", "Please select one");
            modelAndView.setViewName("/DashboardModule2/UserDashboard/BookingSlotScreen1UserDashboard");
            } else {
            Booking currentBooking = CurrentBooking.getCurrentBooking();
            currentBooking.setSpace(count);
            CurrentBooking.setCurrentBooking(currentBooking);
            modelAndView.setViewName("redirect:/Dashboard/c/BookingSlot/Checkout");
            }
            return modelAndView;

        }
        @RequestMapping(value = "/Dashboard/c/BookingSlot/Checkout", method = RequestMethod.GET)
        public ModelAndView displayCheckout(ModelAndView modelAndView)  {

        Booking booking = CurrentBooking.getCurrentBooking();
        CarOwner currentUser = CurrentUser.getCurrentUser();
        long totalTimeMilli = Math.abs(booking.getCheckOut().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()- booking.getCheckIn().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        long totalTime =  TimeUnit.HOURS.convert(totalTimeMilli, TimeUnit.MILLISECONDS);
        long totalCost = 25*totalTime;
        modelAndView.addObject("name", (currentUser.getFirstName()+ " "+currentUser.getLastName()));
        modelAndView.addObject("location", location);
        modelAndView.addObject("totalTime", totalTime);
        modelAndView.addObject("totalCost", totalCost);
        modelAndView.setViewName("/DashboardModule2/UserDashboard/CheckoutUserDashboard");
        return modelAndView;

        }

    @RequestMapping(value = "/Dashboard/c/BookingSlot/Confirmation", method = RequestMethod.POST)
    public ModelAndView ProcessCheckout(ModelAndView modelAndView)  {

        Booking booking = CurrentBooking.getCurrentBooking();
        CarOwner currentUser = CurrentUser.getCurrentUser();
        long totalTimeMilli = Math.abs(booking.getCheckOut().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()- booking.getCheckIn().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        long totalTime =  TimeUnit.HOURS.convert(totalTimeMilli, TimeUnit.MILLISECONDS);
        long totalCost = 25*totalTime;
        if(currentUser.getWallet()<totalCost)
        {
            modelAndView.addObject("message", "Please add money before proceeding");
            modelAndView.setViewName("redirect:/Dashboard/c/BookingSlot/Checkout");
        }
        else {
            currentUser.setWallet(currentUser.getWallet()-(long)100);
            CurrentUser.setCurrentUser(currentUser);
            Booking currentBooking = CurrentBooking.getCurrentBooking();
            currentBooking.setUsername(currentUser.getUsername());
            ParkingSpace bookSpace = parkingSpaceRepository.findById(currentBooking.getSpace());
            if(currentBooking.getSlotType().equals("Hatchback"))
                bookSpace.setAvailableHatchback(bookSpace.getAvailableHatchback()-1);
            else if(currentBooking.getSlotType().equals("Sedan"))
                bookSpace.setAvailableSedan(bookSpace.getAvailableSedan()-1);
            else if(currentBooking.getSlotType().equals("SUV"))
                bookSpace.setAvailableSUV(bookSpace.getAvailableSUV()-1);
            else
                modelAndView.addObject("message", "Something's wrong, please try again");
            modelAndView.setViewName("redirect:/Dashboard/c/BookingSlot/Checkout");

            parkingSpaceRepository.save(bookSpace);
            bookingRepository.save(currentBooking);
            LocalDateTime time = LocalDateTime.of(2021, 12, 05, 20, 21);
            long nowMilli = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long diffMilli = Math.abs(time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            long diff = TimeUnit.MINUTES.convert(diffMilli, TimeUnit.MILLISECONDS);
            if(diff < 5)
            {   // final String ACCOUNT_SID = System.getenv("AC22a079333dfce2bb0f6f868e667d1360");
                 // final String AUTH_TOKEN = System.getenv("e7a500e12786e1ba915f5d2e69c79b24");
                Twilio.init(
                        System.getenv("TWILIO_ACCOUNT_SID"),
                        System.getenv("TWILIO_AUTH_TOKEN"));
                Message message = Message
                        .creator(new PhoneNumber("+91"+ currentUser.getPhoneNumber()), // to
                                new PhoneNumber("+18507897596"), // from
                                "Twilio working with time trigger")
                        .create();
                //"Gentle Reminder! Your booking time will finish in 30 minutes. Additional Costs may apply if checkout is delayed."
                System.out.println(message.getSid());}
            modelAndView.setViewName("redirect:/Dashboard/c/AllBookings");
        }
        return modelAndView;

    }

}