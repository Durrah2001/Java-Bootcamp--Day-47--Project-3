package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.ApiResponse.ApiException;
import org.example.project3.DTO.CustomerDTO_In;
import org.example.project3.Model.Customer;
import org.example.project3.Model.Employee;
import org.example.project3.Model.User;
import org.example.project3.Repository.CustomerRepository;
import org.example.project3.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;


    public void customerRegister(CustomerDTO_In customerDTOIn) {
        //  a general user
        User user = new User();

        user.setUsername(customerDTOIn.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(customerDTOIn.getPassword()));
        user.setEmail(customerDTOIn.getEmail());

        user.setName(customerDTOIn.getName());
        user.setRole("CUSTOMER");


        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTOIn.getPhoneNumber());
        customer.setUser(user);


        // Save user and customer
        userRepository.save(user);

        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public void updateCustomer(Integer customerId, CustomerDTO_In customerDTOIn, Integer userId) {

        Customer existingCustomer = customerRepository.findCustomerById(customerId);
        if (existingCustomer == null)
            throw new ApiException("Customer not found!");
        User existingUser = userRepository.findUserById(existingCustomer.getUser().getId());
        if (existingUser == null)
            throw new ApiException("User not found!");

        if (existingUser.getId() != (userId)) {
            throw new ApiException("You are not authorized to update this customer!");
        }

        existingUser.setUsername(customerDTOIn.getUsername());
        existingUser.setName(customerDTOIn.getName());
        existingUser.setEmail(customerDTOIn.getEmail());
        existingUser.setPassword(new BCryptPasswordEncoder().encode(customerDTOIn.getPassword()));

        existingCustomer.setPhoneNumber(customerDTOIn.getPhoneNumber());

        userRepository.save(existingUser);
        customerRepository.save(existingCustomer);
    }


    public void deleteCustomer(Integer userID){

        User user = userRepository.findUserById(userID);
        if (user == null) {
            throw new ApiException("User not found!");
        }

        Customer customer = customerRepository.findCustomerById(userID);
        if (customer== null) {
            throw new ApiException("Customer not found!");
        }
        userRepository.delete(user);
    }




}
