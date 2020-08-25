package com.kernadler.demo.controller;

import com.kernadler.demo.model.Attendance;
import com.kernadler.demo.model.AttendanceData;
import com.kernadler.demo.model.Employee;
import com.kernadler.demo.repository.EmployeeRepository;
import com.kernadler.demo.service.AttendanceService;
import com.kernadler.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private EmployeeRepository employees;

  @Autowired
  private AttendanceService attendanceService;


  @GetMapping(value = {"/", "/login"})
  public ModelAndView login() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("login");
    return modelAndView;
  }


  @GetMapping(value = "/registration")
  public ModelAndView registration() {
    ModelAndView modelAndView = new ModelAndView();
    Employee employee = new Employee();
    modelAndView.addObject("employee", employee);
    modelAndView.setViewName("registration");
    return modelAndView;
  }

  @PostMapping(value = "/registration")
  public ModelAndView createNewEmployee(@Valid Employee employee, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();
    Employee employeeExists = employeeService.findEmployeeByName(employee.getEmpName());
    if (employeeExists != null) {
      bindingResult
          .rejectValue("empName", "error.emp",
              "There is already a Employee registered with the user name provided");
    }
    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("registration");
    } else {
      employeeService.saveEmployee(employee);
      modelAndView.addObject("successMessage", "EMployee has been registered successfully");
      modelAndView.addObject("employee", new Employee());
      modelAndView.setViewName("registration");

    }
    return modelAndView;
  }

  @GetMapping(value = "/admin/attendance/{empName}")
  public ModelAndView attendance(@PathVariable String empName) {
    ModelAndView modelAndView = new ModelAndView();
    AttendanceData attendanceData = new AttendanceData();
    modelAndView.addObject("attendanceData", attendanceData);
    modelAndView.addObject("empName", empName);
    modelAndView.setViewName("admin/attendance");
    return modelAndView;
  }

  @PostMapping(value = "/admin/attendance", headers = ("content-type=multipart/*"))
  public ModelAndView createNewEmployeeAttendance(
      @Valid AttendanceData attendanceData,
      @RequestParam("file") MultipartFile file,
      BindingResult bindingResult) throws Exception {

    ModelAndView modelAndView = new ModelAndView();
    Employee employeeExists = employeeService.findEmployeeByName(attendanceData.getEmpName());

    Attendance attendance = new Attendance();

    if (employeeExists == null) {
      bindingResult
          .rejectValue("empName", "error.emp",
              "There is already a Employee registered with the user name provided");
    }
    if (!bindingResult.hasErrors()) {
      attendance = attendanceService.saveAtt(
          file,
          employeeExists
      );


      assert employeeExists != null;
      modelAndView.addObject(
          "message",
          "Attendance has been added successfully: " + employeeExists.getFirstName() +
              " " + employeeExists.getLastName() +
              " , with a Monthly salary of: " + attendance.getMonthlySalary());
    }
    modelAndView.setViewName("admin/attendance");
    return modelAndView;
  }

  @GetMapping(value = "/admin/home")
  public ModelAndView home() {
    ModelAndView modelAndView = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Employee employee = employeeService.findEmployeeByName(auth.getName());
    modelAndView.addObject("employee", "Welcome :" + employee.getEmpName() + "/" + employee.getFirstName() + " " + employee.getLastName());
    modelAndView.addObject("adminMessage", "Content Available Only for Employers with Admin Role");
    modelAndView.setViewName("admin/home");
    return modelAndView;
  }

  @RequestMapping(value = "/admin/employees")
  public ModelAndView viewEmployees() {
    ModelAndView modelAndView = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Employee employee = employeeService.findEmployeeByName(auth.getName());
    modelAndView.addObject("employee", "Welcome :" + employee.getEmpName() + "/" + employee.getFirstName() + " " + employee.getLastName());
    modelAndView.addObject("message", "Content Available Only for Employee with Admin Role");

    List<Employee> allEmployees = employees.findAll();
    modelAndView.addObject("employees", allEmployees);
    modelAndView.setViewName("admin/employees");
    return modelAndView;
  }

}
