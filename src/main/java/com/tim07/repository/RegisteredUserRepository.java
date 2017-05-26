package com.tim07.repository;

import com.tim07.domain.DAO.RegisteredUserSearchDAO;
import com.tim07.domain.Entity.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Ivana Zeljkovic on 09-Apr-17.
 */
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

    RegisteredUser save(RegisteredUser user);

    RegisteredUser findByUsername(String username);

    @Query("SELECT r.name AS name, r.lastname AS lastname, r.username AS username " +
            "FROM RegisteredUser r WHERE r.username <> :username AND lower(concat(r.name, ' ', r.lastname))" +
            "LIKE concat('%', lower(:parameter), '%')")
    List<RegisteredUserSearchDAO> findUsers(@Param("username") String username, @Param("parameter") String parameter);
}
