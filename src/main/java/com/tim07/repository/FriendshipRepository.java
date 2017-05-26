package com.tim07.repository;

import com.tim07.domain.Entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Ivana Zeljkovic on 17-Apr-17.
 */
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Friendship save(Friendship friendship);

    void delete(Friendship friendship);
}
