package com.sacylconecta.service;

import com.sacylconecta.model.User;
import com.sacylconecta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByRegistrationId(String registrationId) {
        return userRepository.findByRegistrationId(registrationId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
