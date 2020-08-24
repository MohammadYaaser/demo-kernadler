package com.kernadler.demo.service;

import com.kernadler.demo.model.Role;
import com.kernadler.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyEmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String empName) {
        Employee employee = employeeService.findEmployeeByName(empName);
        List<GrantedAuthority> authorities = getEmployeeAuthority(employee.getRoles());
        return buildEmployeeForAuthentication(employee, authorities);
    }

    private List<GrantedAuthority> getEmployeeAuthority(Set<Role> empRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : empRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new ArrayList<>(roles);
    }

    private UserDetails buildEmployeeForAuthentication(Employee employee, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(employee.getEmpName(), employee.getPassword(),
                employee.getActive(), true, true, true, authorities);
    }
}
