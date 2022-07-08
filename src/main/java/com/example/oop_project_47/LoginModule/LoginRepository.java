package com.example.oop_project_47.LoginModule;


import com.example.oop_project_47.LoginModule.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginCredentials, String> {
    Optional<LoginCredentials> findUserByUsername(String username);

    LoginCredentials findByUsername(String username);
    LoginCredentials save(LoginCredentials loginCredentials);

    void delete(LoginCredentials loginCredentials);
    List<LoginCredentials> findAllByIdGreaterThan(int x);
}
