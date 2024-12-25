package org.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(15) not null")
    private String position;

    @Column(columnDefinition = "DOUBLE not null")
    private Double salary;

    ///////////////


    public Employee(String position, Double salary) {
        this.position = position;
        this.salary = salary;
    }

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;




}
