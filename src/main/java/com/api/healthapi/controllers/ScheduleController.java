package com.api.healthapi.controllers;



import com.api.healthapi.models.Schedule;
import com.api.healthapi.services.ScheduleService;
import com.api.healthapi.utils.JWTUtil;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
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


    @GetMapping("/today")
    public List<Schedule> getSchedulesForToday(@RequestHeader (value = "Authorization") String token) {
        try {

            LocalDate today = LocalDate.now();
//            return scheduleRepository.findScheduleByDate(today);
            return null;

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Error getting schedules", e);
        }
    }

    @PostMapping("/create")
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return null;

    }
}
