package org.example.project3.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.project3.ApiResponse.ApiResponse;
import org.example.project3.DTO.CustomerDTO_In;
import org.example.project3.DTO.EmployeeDTO_In;
import org.example.project3.Model.User;
import org.example.project3.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank-system/user")

public class UserController {

    private final UserService userService;


    @GetMapping("/get/all-users")
    public ResponseEntity getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/get/user-byId/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }










}
