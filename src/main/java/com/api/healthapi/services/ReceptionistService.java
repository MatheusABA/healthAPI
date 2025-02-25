package com.api.healthapi.services;

import com.api.healthapi.models.Receptionist;
import com.api.healthapi.repositories.ReceptionistRepository;
import com.api.healthapi.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ReceptionistService {

    // Calling Receptionist DB Queries Instance
    private final ReceptionistRepository receptionistRepository;

    // Calling JWTUtil token creation
    private final JWTUtil jwtUtil;

    // Calling Argon2 Hash Instance
    private final Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    // Class Constructor
    public ReceptionistService(ReceptionistRepository receptionistRepository, JWTUtil jwtUtil) {
        this.receptionistRepository = receptionistRepository;
        this.jwtUtil = jwtUtil;
    }


    /**
     * Function that create a receptionist
     * @param receptionist Receptionist class params
     * @return Receptionist
     */
    public Receptionist createReceptionist(Receptionist receptionist) {

        try {

            // create hash
            String hashPassword = argon2.hash(1, 1024, 1, receptionist.getPassword());
            // set as password
            receptionist.setPassword(hashPassword);

            // try to save in db
            return receptionistRepository.save(receptionist);

        } catch (Exception e){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Error creating receptionist", e);
        }
    }

    /**
     * Validate receptionist info
     *
     * @param email    Receptionist email
     * @param password Receptionist password
     */
    public String authenticateReceptionist(String email, String password) {

        Optional<Receptionist> receptionistAuth = receptionistRepository.findReceptionistByEmail(email);

        if (receptionistAuth.isEmpty()) {
            return "Invalid or expired token";
        }

        Receptionist receptionist = receptionistAuth.get();

        if (argon2.verify(receptionist.getPassword(), password)) {
            return jwtUtil.create(String.valueOf(receptionist.getId()), receptionist.getEmail());
        }

        return "Invalid or expired token";
    }


    public boolean isEmailTaken(String email) {
        Optional<Receptionist> receptionist = receptionistRepository.findReceptionistByEmail(email);
        return receptionist.isPresent();
    }


}
