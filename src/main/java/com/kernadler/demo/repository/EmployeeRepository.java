package com.kernadler.demo.repository;

import com.kernadler.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Employee findByEmail(String email);

  Employee findByEmpName(String empName);
}