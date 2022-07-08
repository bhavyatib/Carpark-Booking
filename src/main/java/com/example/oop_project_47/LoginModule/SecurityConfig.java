package com.example.oop_project_47.LoginModule;

import com.example.oop_project_47.Admin.Admin;
import com.example.oop_project_47.Car_Owner.CarOwner;
import com.example.oop_project_47.Car_Owner.CustomOAuth2UserService;
import com.example.oop_project_47.Car_Owner.OAuth2LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Qualifier("carOwnerDetailsService")
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/SignUppage_1","/register","/forgot", "/ForgetPass","/oauth/**","/oauth2/**").permitAll()
                //.anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .and()
                .oauth2Login().loginPage("/signin")
                .userInfoEndpoint().userService(oAuth2UserService)
                .and()

                .and()
                .logout().permitAll()
                .permitAll();
    }/*
    Admin admin = new Admin();
    CarOwner carOwner   = new CarOwner();
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception  {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser(admin.getUsername())
                .password(passwordEncoder().encode(admin.getPassword()))
                .roles("ADMIN");
    } */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }
    @Autowired
    private CustomOAuth2UserService oAuth2UserService;
    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
}

