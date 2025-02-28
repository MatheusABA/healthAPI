package com.api.healthapi.controllers;



import com.api.healthapi.models.Schedule;
import com.api.healthapi.services.ScheduleService;
import com.api.healthapi.utils.JWTUtil;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {


    private final ScheduleService scheduleService;

    private final JWTUtil jwtUtil;

    // Constructor
    public ScheduleController(ScheduleService scheduleService, JWTUtil jwtUtil) {
        this.scheduleService = scheduleService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * List today schedules
     * @param token Auth
     * @return List with today schedules
     */
    @GetMapping("/today")
    public ResponseEntity<List<Schedule>> getSchedulesForToday(@RequestHeader (value = "Authorization") String token) {

        if (!jwtUtil.checkToken(token)) {
            return ResponseEntity.status(403).build();
        }

        try {
            List<Schedule> schedules = scheduleService.getSchedulesForToday();
            return ResponseEntity.status(200).body(schedules);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping()
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule, @RequestHeader (value = "Authorization") String token) {
        if (!jwtUtil.checkToken(token)) {
            return ResponseEntity.status(403).build();
        }
        try {
            Schedule scheduleCreated = scheduleService.createSchedule(schedule);
            return ResponseEntity.status(201).body(scheduleCreated);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Integer id, @RequestHeader (value = "Authorization") String token) {
        if (!jwtUtil.checkToken(token)) {
            return ResponseEntity.status(403).build();
        }
        try {
            Optional<Schedule> schedule = scheduleService.getScheduleById(id);
            return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Schedule>> getScheduleByDoctorId(@PathVariable Integer doctorId, @RequestHeader (value = "Authorization") String token) {
        if (!jwtUtil.checkToken(token)) {
            return ResponseEntity.status(403).build();
        }
        try {
            List<Schedule> schedules = scheduleService.getSchedulesByDoctorId(doctorId);
            return ResponseEntity.status(200).body(schedules);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Schedule>> getScheduleByPatientId(@PathVariable Integer patientId, @RequestHeader (value = "Authorization") String token) {
        if (!jwtUtil.checkToken(token)) {
            return ResponseEntity.status(403).build();
        }
        try{
            List<Schedule> schedules = scheduleService.getSchedulesByPatientId(patientId);
            return ResponseEntity.status(200).body(schedules);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Integer id, @RequestHeader (value = "Authorization") String token) {
        if (!jwtUtil.checkToken(token)) {
            return ResponseEntity.status(403).build();
        }
        try {
            boolean deleted = scheduleService.deleteScheduleById(id);
            return deleted ? ResponseEntity.status(200).build() : ResponseEntity.status(404).build();

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}
