package com.example.oop_project_47.Car_Owner;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component

public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    CarOwnerRepository carOwnerRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        customOauth2user oauth2user=(customOauth2user)authentication.getPrincipal();
        String emailId=oauth2user.getEmail();
        CarOwner carOwner=new CarOwner();



        if(carOwnerRepository.findUserByUsername(emailId).equals(null))
        {
            carOwner.setAuthProvider(AuthenticationProvider.GOOGLE);
            carOwner.setEmailId(emailId);
            carOwner.setFirstName(oauth2user.getName());

            carOwnerRepository.save(carOwner);
        }

        else {
            System.out.println("Customer's email: " + emailId);
        }
        super.onAuthenticationSuccess(request,response,authentication);
    }
}
