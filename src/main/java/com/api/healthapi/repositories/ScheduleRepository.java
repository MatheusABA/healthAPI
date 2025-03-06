package com.api.healthapi.repositories;

import com.api.healthapi.models.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

    // Find schedules by date time
    List<Schedule> findScheduleByDate(LocalDate date);

    // Find schedules by doctor id
    List<Schedule> findScheduleByDoctorId(Integer doctorId);

    // Find schedules by patient id
    List<Schedule> findScheduleByPatientId(Integer patientId);

    void deleteScheduleById(Integer id);


    @Query("SELECT s FROM Schedule s " +
            "JOIN FETCH s.doctor d " +
            "JOIN FETCH s.patient p " +
            "WHERE s.date = :today")
    List<Schedule> findScheduleByDateWithDoctorAndPatient(@Param("today") LocalDate today);



}
