package org.example.project3.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CustomerDTO_In {


    @NotEmpty(message = "Username can not be empty!")
    @Size(min = 4, max = 10, message = "Username's length must be between 4 and 10 characters.")
    private String username;

    @NotEmpty(message = "Name can not be empty!")
    @Size(min = 2, max = 20, message = "Name's length must be between 2 and 20 characters.")
    private String name;

    @NotEmpty(message = "Email can not be empty!")
    @Email(message = "Must be a valid email format!")
    private String email;

    @NotEmpty(message = "Password can not be empty!")
    @Size(min = 6)
    private String password;

    @NotEmpty(message = "Phone number can not be empty!")
    @Pattern(regexp = "05\\d{8}")
    private String phoneNumber;

}
