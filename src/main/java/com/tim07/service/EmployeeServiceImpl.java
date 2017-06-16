package com.tim07.service;

import com.tim07.domain.Entity.Employee;
import com.tim07.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by brzak on 15.6.17..
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }
}
