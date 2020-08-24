package com.kernadler.demo.controller;

import com.kernadler.demo.model.Employee;
import com.kernadler.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping(value="/registration")
    public ModelAndView registration(){
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

    @GetMapping(value="/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findEmployeeByName(auth.getName());
        modelAndView.addObject("empName", "Welcome " + employee.getEmpName() + "/" + employee.getName() + " " + employee.getLastName() + " (" + employee.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Employers with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }


}
