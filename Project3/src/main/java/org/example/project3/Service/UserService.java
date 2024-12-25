package org.example.project3.Service;


import lombok.RequiredArgsConstructor;

import org.example.project3.ApiResponse.ApiException;

import org.example.project3.Model.User;
import org.example.project3.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
   ;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException("User not found!"));
    }













}
