package org.example.project3.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.project3.ApiResponse.ApiException;
import org.example.project3.DTO.EmployeeDTO_In;
import org.example.project3.Model.Employee;
import org.example.project3.Model.User;
import org.example.project3.Repository.EmployeeRepository;
import org.example.project3.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final UserRepository userRepository;


    public void employeeRegister(EmployeeDTO_In employeeDTOIn) {

        User user = new User();

        //  username, email, and password
        user.setUsername(employeeDTOIn.getUsername());
        String passwordHash = new BCryptPasswordEncoder().encode(employeeDTOIn.getPassword());
        user.setPassword(passwordHash);

        user.setEmail(employeeDTOIn.getEmail());

        user.setName(employeeDTOIn.getName());

        user.setRole("EMPLOYEE");

        Employee employee = new Employee(null, employeeDTOIn.getPosition(), employeeDTOIn.getSalary(), user);

        employee.setUser(user);

        userRepository.save(user);
        employeeRepository.save(employee);
    }


    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }


    public void updateEmployee(Integer employeeId, EmployeeDTO_In employeeDTOIn, Integer userId) {
        Employee existingEmployee = employeeRepository.findEmployeeById(employeeId);
        if (existingEmployee == null) {
            throw new ApiException("Employee not found!");
        }

        User existingUser = userRepository.findUserById(existingEmployee.getUser().getId());
        if (existingUser == null) {
            throw new ApiException("User not found!");
        }

        if (!existingUser.getId().equals(userId)) {
            throw new ApiException("You can not update this employee!");
        }

        // update user details
        existingUser.setUsername(employeeDTOIn.getUsername());
        existingUser.setName(employeeDTOIn.getName());
        existingUser.setEmail(employeeDTOIn.getEmail());
        existingUser.setPassword(new BCryptPasswordEncoder().encode(employeeDTOIn.getPassword())); // Ensure the password is hashed

        //  employee details
        existingEmployee.setPosition(employeeDTOIn.getPosition());
        existingEmployee.setSalary(employeeDTOIn.getSalary());

        // Save  user and employee
        userRepository.save(existingUser);
        employeeRepository.save(existingEmployee);
    }


    public void deleteEmployee(Integer userId) {

        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("User not found!");
        }

        Employee existingEmployee = employeeRepository.findEmployeeById(userId);
        if (existingEmployee == null) {
            throw new ApiException("Employee not found!");
        }

        userRepository.delete(user);
    }

















}
