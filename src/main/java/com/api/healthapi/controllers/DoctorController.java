package com.api.healthapi.controllers;

import com.api.healthapi.models.Doctor;
import com.api.healthapi.services.DoctorService;
import com.api.healthapi.utils.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    private final JWTUtil jwtUtil;

    public DoctorController(DoctorService doctorService, JWTUtil jwtUtil) {
        this.doctorService = doctorService;
        this.jwtUtil = jwtUtil;
    }


    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestHeader (value = "Authorization") String token) {
        if(!jwtUtil.checkToken(token)) {
            return null;
        }

        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@RequestHeader (value = "Authorization") String token, @PathVariable Integer id) {

        if(!jwtUtil.checkToken(token)) {
            return null;
        }

        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        return doctor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<?> createDoctor(@RequestHeader (value = "Authorization") String token, @RequestBody Doctor doctor) {

        if(!jwtUtil.checkToken(token)) {
            return null;
        }

        try {
            Doctor savedDoctor = doctorService.createDoctor(doctor);
            return ResponseEntity.status(201).body(savedDoctor);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@RequestHeader (value = "Authorization") String token, @PathVariable Integer id) {
        if(!jwtUtil.checkToken(token)) {
            return null;
        }

        try {
            boolean deleted = doctorService.deleteDoctorById(id);
            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }



}
