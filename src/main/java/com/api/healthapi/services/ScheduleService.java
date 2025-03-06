package com.api.healthapi.services;

import com.api.healthapi.dto.ScheduleDTO;
import com.api.healthapi.models.Doctor;
import com.api.healthapi.models.Patient;
import com.api.healthapi.models.Schedule;
import com.api.healthapi.repositories.DoctorRepository;
import com.api.healthapi.repositories.PatientRepository;
import com.api.healthapi.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    // Constructor
    public ScheduleService(ScheduleRepository scheduleRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.scheduleRepository = scheduleRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    /**
     * List today schedules
     * @return list with all schedules by day
     */
    public List<Schedule> getSchedulesForToday() {
        LocalDate today = LocalDate.now();
        return scheduleRepository.findScheduleByDateWithDoctorAndPatient(today);
    }


    public Schedule createSchedule(ScheduleDTO scheduleDTO) {

        Doctor doctor = doctorRepository.findDoctorById(scheduleDTO.getDoctor_id())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepository.findPatientById(scheduleDTO.getPatient_id())
                .orElseThrow(() -> new RuntimeException("Patient not found"));


        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setTime(scheduleDTO.getTime());
        schedule.setDescription(scheduleDTO.getDescription());
        schedule.setStatus(scheduleDTO.getStatus());
        schedule.setDoctor(doctor);
        schedule.setPatient(patient);
        return scheduleRepository.save(schedule);
    }



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
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            scheduleRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }
}
