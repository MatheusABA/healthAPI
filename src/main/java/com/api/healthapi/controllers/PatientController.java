package com.api.healthapi.controllers;

import com.api.healthapi.controllers.auth.AuthController;
import com.api.healthapi.models.Patient;
import com.api.healthapi.services.PatientService;
import com.api.healthapi.utils.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {

    private final PatientService patientService;
    private final JWTUtil jwtUtil;

    public PatientController(PatientService patientService, JWTUtil jwtUtil) {
        this.patientService = patientService;
        this.jwtUtil = jwtUtil;
    }


    /**
     * Get all patients
     * @param token Auth
     * @return List with patients
     */
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {

        if (!jwtUtil.checkToken(token)) {
            return ResponseEntity.status(403).build();
        }

        try {
            List<Patient> patients = patientService.getAllPatients(page, size);
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, e);
            return ResponseEntity.status(500).build();
        }

    }


    /**
     * Get patient by id
     * @param token Auth
     * @param id Patient id
     * @return Patient by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@RequestHeader(value = "Authorization") String token, @PathVariable Integer id) {

        if (!jwtUtil.checkToken(token)) {
            return ResponseEntity.status(403).build();
        }

        try {
            Optional<Patient> patient = patientService.getPatientById(id);
            return patient.map(ResponseEntity::ok).orElse(ResponseEntity.status(404).build());

        } catch (Exception e) {
            Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, e);
            return ResponseEntity.status(500).build();
        }

    }


    @PostMapping()
    public ResponseEntity<?> createPatient(@RequestBody Patient patient, @RequestHeader(value = "Authorization") String token) {
        if (!jwtUtil.checkToken(token)) {
            return ResponseEntity.status(403).build();
        }

        try {
            Patient createdPatient = patientService.createPatient(patient);
            return ResponseEntity.status(201).body(createdPatient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatientById(@RequestHeader(value = "Authorization") String token, @PathVariable Integer id) {

        if (!jwtUtil.checkToken(token)) {
            return ResponseEntity.status(403).build();
        }

        try {
            patientService.deletePatientById(id);
            return ResponseEntity.status(204).build();
        } catch ( Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

}
