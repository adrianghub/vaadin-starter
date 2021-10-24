package com.kodilla.project.xpanser.backend.repository;

import com.kodilla.project.xpanser.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getByUsername(String username);
    User getByEmail(String email);
}
