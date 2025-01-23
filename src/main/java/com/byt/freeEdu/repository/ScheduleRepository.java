package com.byt.freeEdu.repository;

import com.byt.freeEdu.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findBySchoolClass_SchoolClassId(int schoolClassId);
}
