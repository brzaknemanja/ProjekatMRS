package com.tim07.repository;

import com.tim07.domain.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by brzak on 15.6.17..
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUsername(String username);
}
