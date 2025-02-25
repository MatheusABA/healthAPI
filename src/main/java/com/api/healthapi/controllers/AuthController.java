package com.api.healthapi.controllers;


import com.api.healthapi.dto.LoginRequest;
import com.api.healthapi.models.Receptionist;
import com.api.healthapi.services.ReceptionistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/")
public class AuthController {



    private final ReceptionistService receptionistService;


    public AuthController(ReceptionistService receptionistService) {
        this.receptionistService = receptionistService;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {

            String token = receptionistService.authenticateReceptionist(loginRequest.getEmail(), loginRequest.getPassword());

            if ("Invalid or expired token".equals(token) || token == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
            }

            return ResponseEntity.ok(token);

        } catch (RuntimeException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Receptionist receptionist) {
        try {

            if(receptionistService.isEmailTaken(receptionist.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already taken");
            }

            Receptionist createdReceptionist = receptionistService.createReceptionist(receptionist);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReceptionist);

        } catch (RuntimeException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
