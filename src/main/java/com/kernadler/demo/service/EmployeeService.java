package com.kernadler.demo.service;

import com.kernadler.demo.model.Role;
import com.kernadler.demo.model.Employee;
import com.kernadler.demo.repository.RoleRepository;
import com.kernadler.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Service
public class EmployeeService {

  private EmployeeRepository employeeRepository;
  private RoleRepository roleRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository,
                         RoleRepository roleRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.employeeRepository = employeeRepository;
    this.roleRepository = roleRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public Employee findEmployeeByEmail(String email) {
    return employeeRepository.findByEmail(email);
  }

  public Employee findEmployeeByName(String empName) {
    return employeeRepository.findByEmpName(empName);
  }

  public Employee saveEmployee(Employee employee) {
    employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
    employee.setRowState(true);
    Role empRole = roleRepository.findByRole("ADMIN");
    employee.setRoles(new HashSet<>(Collections.singletonList(empRole)));
    return employeeRepository.save(employee);
  }

}