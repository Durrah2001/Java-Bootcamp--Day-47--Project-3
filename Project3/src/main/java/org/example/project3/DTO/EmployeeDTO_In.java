package org.example.project3.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmployeeDTO_In  {


    @NotEmpty(message = "Username can not be empty!")
    @Size(min = 4, max = 10, message = "Username's length must be between 4 and 10 characters.")
    private String username;

    @NotEmpty(message = "Name can not be empty!")
    @Size(min = 2, max = 20, message = "Length must be between 2 and 20 characters.")
    private String name;

    @NotEmpty(message = "Email can not be empty!")
    @Email(message = "Must be a valid email format!")
    private String email;

    @NotEmpty(message = "Password can not be empty!")
    @Size(min = 6)
    private String password;

    @NotEmpty(message = "Position can not be empty!")
    private String position;

    @Positive(message = "Salary can not be negative number!")
    private Double salary;



}
