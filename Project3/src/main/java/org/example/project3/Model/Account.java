package org.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//
//    @NotEmpty(message = "Account number can not be empty!")
//    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", message = "Account number must follow the format XXXX-XXXX-XXXX-XXXX")
//    @Column(columnDefinition = "varchar(16) not null")
    private String accountNumber;

//    @Positive(message = "Balance must be a positive number!")
//    @Column(columnDefinition = "DOUBLE not null")
    private Double balance;

    private Boolean isActive= false;

    ////////////////////////////

    @ManyToOne
    @JsonIgnore
    private Customer customer;








}
