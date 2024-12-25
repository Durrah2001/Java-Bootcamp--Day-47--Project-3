package org.example.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.project3.ApiResponse.ApiResponse;
import org.example.project3.DTO.CustomerDTO_In;
import org.example.project3.Model.Customer;
import org.example.project3.Model.User;
import org.example.project3.Service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank-system/customer")

public class CustomerController {

    private final CustomerService customerService;


    @PostMapping("/customer-register")
    public ResponseEntity customerRegister(@RequestBody @Valid CustomerDTO_In customerDTOIn) {
        customerService.customerRegister(customerDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("Customer registered successfully"));
    }

    @GetMapping("/get/all-customers")
    public ResponseEntity getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.status(200).body(customers);
    }
    @PutMapping("/update/customer/{customerId}")
    public ResponseEntity updateCustomer(
            @PathVariable Integer customerId,
            @RequestBody CustomerDTO_In customerDTOIn,
            @AuthenticationPrincipal User user) {
        customerService.updateCustomer(customerId, customerDTOIn, user.getId());
        return ResponseEntity.ok(new ApiResponse("Customer updated successfully!"));
    }

    @DeleteMapping("/delete/customer")
    public ResponseEntity  deleteCustomer(@AuthenticationPrincipal User user) {
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.ok(new ApiResponse("Customer deleted successfully!"));
    }





}
