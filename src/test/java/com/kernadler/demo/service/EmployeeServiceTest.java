package com.kernadler.demo.service;

import com.kernadler.demo.model.Employee;
import com.kernadler.demo.repository.EmployeeRepository;
import com.kernadler.demo.repository.RoleRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeServiceTest {

  @Mock
  private EmployeeRepository mockEmployeeRepository;

  @Mock
  private RoleRepository mockRoleRepository;

  @Mock
  private BCryptPasswordEncoder mockBC;

  private EmployeeService employeeServiceTester;
  private Employee employee;

  @Before(value = "")
  public void setUp() {
    initMocks(this);

    employeeServiceTester = new EmployeeService(mockEmployeeRepository,
        mockRoleRepository,
        mockBC);

    employee = Employee.builder()
        .id(1)
        .empName("Mohammad")
        //.rowState(true)
        //.empType("Software Development Engineer")
        //.empBaseSalary(2222)
        .email("mohammadyaseer@icloud.com")
        .build();

    Mockito.when(mockEmployeeRepository.save(any()))
        .thenReturn(employee);

    Mockito.when(mockEmployeeRepository.findByEmail(anyString()))
        .thenReturn(employee);
  }

  @Test
  public void testFindEmployeeByEmail() {
    final String email = "mohammadyaseer@icloud.com";

    Employee result = employeeServiceTester.saveEmployee(Employee.builder().build());

    assertEquals(email, result.getEmail());
  }
}
