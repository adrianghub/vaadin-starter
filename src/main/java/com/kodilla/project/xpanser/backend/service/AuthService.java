package com.kodilla.project.xpanser.backend.service;

import com.kodilla.project.xpanser.backend.entity.User;
import com.kodilla.project.xpanser.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public class AuthException extends Exception {
    }

    private final UserRepository userRepository;


    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void authenticate(String email, String password) throws AuthException {
        User user = userRepository.getByEmail(email);

        if(user != null && user.comparePassword(password)) {

        } else {
            throw new AuthException();
        }
    }
}
