package com.emlakjet.ejcase.repository;

import com.emlakjet.ejcase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
