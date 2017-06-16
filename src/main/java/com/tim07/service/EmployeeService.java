package com.tim07.service;

import com.tim07.domain.Entity.Employee;

/**
 * Created by brzak on 15.6.17..
 */

public interface EmployeeService {

    Employee findByUsername(String username);
}
