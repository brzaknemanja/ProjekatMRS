package com.tim07.repository;

import com.tim07.domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Ivana Zeljkovic on 08-Apr-17.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
