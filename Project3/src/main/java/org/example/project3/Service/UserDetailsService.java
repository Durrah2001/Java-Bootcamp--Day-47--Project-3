package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.ApiResponse.ApiException;
import org.example.project3.Model.User;
import org.example.project3.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username); //check from DB

        if(user== null)
            throw new ApiException("Wrong username or password!");

        return user;
        //implement in user


    }




}
