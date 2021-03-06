package com.kernadler.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_id")
    private Integer id;

    @Column(name = "emp_name")
    @Length(min = 5, message = "*Your employee name must have at least 5 characters")
    @NotEmpty(message = "*Please provide a employee name")
    private String empName;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "first_name")
    @NotEmpty(message = "*Please provide first name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide last name")
    private String lastName;

    @Column(name = "row_state")
    private Boolean rowState;

    @Column(name = "emp_type")
    @NotEmpty(message = "*Please provide Employee Type")
    private String empType;

    @Column(name = "base_salary")
    @NotNull(message = "*Please provide Base salary")
    private Long baseSalary;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "emp_role", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
