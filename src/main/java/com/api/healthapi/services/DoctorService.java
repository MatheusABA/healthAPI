package com.api.healthapi.services;

import com.api.healthapi.models.Doctor;
import com.api.healthapi.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DoctorService {


    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    /**
     * Get all doctors
     * @return List with all doctors
     */
    public List<Doctor> getAllDoctors() {
        try {
            return doctorRepository.findAll();

        } catch (Exception e) {
            Logger.getLogger(DoctorService.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException("Error getting all doctors", e);
        }
    }


    /**
     * Get doctor by id
     * @param id Doctor id
     * @return Doctor if found
     */
    public Optional<Doctor> getDoctorById(Integer id) {
        try {

            return doctorRepository.findById(id);

        } catch (Exception e) {
            Logger.getLogger(DoctorService.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException("Error getting doctor", e);
        }
    }

    /**
     * Create a new doctor
     * @param doctor Doctor params
     * @return Save
     */
    public Doctor createDoctor(Doctor doctor) {

        if (doctorRepository.findDoctorByEmail(doctor.getEmail()).isPresent()) {
            throw new IllegalArgumentException("This email is already used");
        }
        if (doctorRepository.findDoctorByCrm(doctor.getCrm()).isPresent()) {
            throw new IllegalArgumentException("This CRM is already used");
        }

        return doctorRepository.save(doctor);

    }


    public boolean deleteDoctorById(Integer id) {
        try {
            Optional<Doctor> doctor = doctorRepository.findById(id);

            if (doctor.isEmpty()) {
                return false;
            } else {
                doctorRepository.delete(doctor.get());
                return true;
            }

        } catch (Exception e) {
            Logger.getLogger(DoctorService.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException("Error deleting doctor", e);
        }

    }
}
