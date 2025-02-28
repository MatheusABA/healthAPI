package com.api.healthapi.services;

import com.api.healthapi.models.Schedule;
import com.api.healthapi.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // Constructor
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    /**
     * List today schedules
     * @return list with all schedules by day
     */
    public List<Schedule> getSchedulesForToday() {
        LocalDate today = LocalDate.now();
        return scheduleRepository.findScheduleByDate(today);
    }


    public Schedule createSchedule(Schedule schedule) {
        // logic missing
        return scheduleRepository.save(schedule);
    }


    /**
     *
     * @param id
     * @return
     */
    public Optional<Schedule> getScheduleById(Integer id) {
        return scheduleRepository.findById(id);
    }


    public List<Schedule> getSchedulesByDoctorId(Integer doctorId) {
        return scheduleRepository.findScheduleByDoctorId(doctorId);
    }

    public List<Schedule> getSchedulesByPatientId(Integer patientId) {
        return scheduleRepository.findScheduleByPatientId(patientId);
    }


    public boolean deleteScheduleById(Integer id) {
        return scheduleRepository.deleteScheduleById(id);


    }
}
