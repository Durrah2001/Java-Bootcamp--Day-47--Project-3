package org.example.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.project3.ApiResponse.ApiResponse;
import org.example.project3.DTO.EmployeeDTO_In;
import org.example.project3.Model.Employee;
import org.example.project3.Model.User;
import org.example.project3.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank-system/employee")
public class EmployeeController {


    private final EmployeeService employeeService;


    @PostMapping("/employee-register")
    public ResponseEntity employeeRegister(@RequestBody @Valid EmployeeDTO_In employeeDTOIn){

        employeeService.employeeRegister(employeeDTOIn);
        return ResponseEntity.status(200).body(new ApiResponse("Employee registered successfully!"));
    }

    @GetMapping("/get/all-employees")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }

    @PutMapping("/update/employee/{employeeId}")
    public ResponseEntity updateEmployee(
            @PathVariable Integer employeeId,
            @RequestBody EmployeeDTO_In employeeDTOIn,
            @AuthenticationPrincipal User user) {
        employeeService.updateEmployee(employeeId, employeeDTOIn, user.getId());
        return ResponseEntity.ok(new ApiResponse("Employee updated successfully!"));
    }

    @DeleteMapping("/delete/employee")
    public ResponseEntity  deleteEmployee(@AuthenticationPrincipal User user) {
        employeeService.deleteEmployee(user.getId());
        return ResponseEntity.ok(new ApiResponse("Employee deleted successfully!"));
    }



}
