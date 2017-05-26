package com.tim07.repository;

import com.tim07.domain.Entity.DaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by freez on 24-Apr-17.
 */
public interface DayScheduleRepository extends JpaRepository<DaySchedule,Long> {
}
