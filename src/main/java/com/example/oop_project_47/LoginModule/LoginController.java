package com.example.oop_project_47.LoginModule;

import com.example.oop_project_47.Admin.Admin;
import com.example.oop_project_47.Car_Owner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.mail.internet.AddressException;

@Controller
public class LoginController implements WebMvcConfigurer {
    private String username;
    private String emailId;
    private String password;
    private boolean status;
    private int carOwnerId;

    public int getCarOwnerId() {
        return carOwnerId;
    }
    @Value("${host.name}")
    private String localhost;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private CarOwnerRepository carOwnerRepository;


    Admin admin = new Admin();


    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView displayLogin(ModelAndView modelAndView, LoginCredentials loginCredentials) {
        LoginCredentials existingUser = loginRepository.findByUsername(admin.getUsername());
        if(existingUser == null) {
            loginCredentials.setAdminCredentials(admin.getId(), "ADMIN", admin.getUsername(), admin.getPassword(), admin.getEmailId(), admin.getPhoneNumber());
            loginRepository.save(loginCredentials);
        }

        modelAndView.addObject("LoginCredentials", loginCredentials);
        modelAndView.setViewName("/Authenticate");
        return modelAndView;
    }

    @RequestMapping(value = "/Dashboard", method = RequestMethod.POST)
    public ModelAndView loginUser(ModelAndView modelAndView, LoginCredentials loginCredentials) throws AddressException {
        LoginCredentials existingUser = loginRepository.findByUsername(loginCredentials.getUsername());
        //List<CarOwner> carOwnerList = carOwnerRepository.findUserById(userId);
        //CarOwner carOwner = carOwnerRepository.findById(userId);
        carOwnerId = existingUser.getId();
        if (existingUser != null) {
            if(loginCredentials.getUsername().equals(existingUser.getUsername())  && loginCredentials.getPassword().equals(existingUser.getPassword()))  {
                if(existingUser.getUser_role().equals("ADMIN"))  {
                    modelAndView.setViewName("redirect:/Dashboard/a/");

                }
                else if(existingUser.getUser_role().equals("CAR_OWNER"))  {
                        CarOwner currentUser = carOwnerRepository.findUserByUsername(existingUser.getUsername());
                        if(currentUser.getWallet()==null)
                            currentUser.setWallet((long)0);
                        CurrentUser.setCurrentUser(currentUser);
                    modelAndView.setViewName("redirect:/Dashboard/c/");
                    //Enter Owner Dashboard here
                }
                else if(existingUser.getUser_role().equals("WORKER"))  {
                    modelAndView.setViewName("redirect:/Dashboard/w/");
                    //Enter Worker Dashboard here
                }
            }
            else {
                modelAndView.setViewName("/index");

            }
        }
        else {
            modelAndView.setViewName("/index");
        }
        //modelAndView.setViewName("/DashboardModule2/AdminDashboard/DashboardAdmin");
        return modelAndView;
    }
    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public ModelAndView forgotPass(ModelAndView modelAndView, CarOwner carOwner) {

        modelAndView.addObject("CarOwner", carOwner);
        modelAndView.setViewName("/ForgetPass");
        return modelAndView;
    }

    @RequestMapping(value = "/pass_change", method = RequestMethod.POST)
    public ModelAndView PassChange(ModelAndView modelAndView, CarOwner carOwner) {
        CarOwner existingUser =carOwnerRepository.findUserByEmailId(carOwner.getEmailId());
        if (existingUser == null) {
            modelAndView.addObject("message", "This email Does not exist!");

            modelAndView.setViewName("/index");
            return modelAndView;
        }
        else  {
            emailId =existingUser.getEmailId();
            carOwner =carOwnerRepository.findUserByEmailId(emailId);
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmailId());
            mailMessage.setSubject("Request for Password change ");
            mailMessage.setText("Please click this link to change your password : "
                    + "http://" + localhost + ":8080/change-password?token=" + confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);

            modelAndView.addObject("emailId", emailId);
            modelAndView.setViewName("/PasswordChange");
            return modelAndView;
    }
    }
    @RequestMapping(value = "/PhoneVerification", method = RequestMethod.GET)
    public ModelAndView phoneVerification(ModelAndView modelAndView, CarOwner carOwner) {

        modelAndView.addObject("CarOwner", carOwner);
        modelAndView.setViewName("/SignInModule1/OTPphoneVerification");
        return modelAndView;
    }
    @RequestMapping(value = "/PhoneVerification",  method = { RequestMethod.POST})
    public void succesfulVerification(ModelAndView modelAndView,CarOwner carOwner)
    {
        CarOwner fin= carOwnerRepository.findUserByEmailId(emailId);
        fin.setPhoneNumber(carOwner.getPhoneNumber());
        carOwnerRepository.save(fin);

    }

}


