package com.api.healthapi.repositories;

import com.api.healthapi.models.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

    List<Schedule> findScheduleByDoctorId(Integer doctorId);

    List<Schedule> findScheduleByPatientId(Integer patientId);
}
