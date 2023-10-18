package com.example.http.repository;
import com.example.http.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String account);
    User getUserByPhoneNumber (String account);
    Optional<User> findByEmail (String email);

}
