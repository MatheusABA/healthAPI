package com.api.healthapi.repositories;

import com.api.healthapi.models.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;


public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

    // Find schedules by date time
    List<Schedule> findScheduleByDate(LocalDate date);

    // Find schedules by doctor id
    List<Schedule> findScheduleByDoctorId(Integer doctorId);

    // Find schedules by patient id
    List<Schedule> findScheduleByPatientId(Integer patientId);

    boolean deleteScheduleById(Integer id);


}
