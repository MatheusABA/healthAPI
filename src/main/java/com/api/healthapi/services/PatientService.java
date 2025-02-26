package com.api.healthapi.services;

import com.api.healthapi.models.Patient;
import com.api.healthapi.repositories.PatientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt")));
        return patientRepository.findAll(pageable).getContent();
    }

    public Optional<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }

    public List<Patient> searchPatient(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt")));
        return patientRepository.findPatientByName(name, pageable).getContent();
    }


    public Patient createPatient(Patient patient) {
        if (patientRepository.findPatientByEmail(patient.getEmail()).isPresent()) {
            throw new IllegalArgumentException("This email address is already in use");
        }
        if (patientRepository.findPatientByPhone(patient.getPhone()).isPresent()) {
            throw new IllegalArgumentException("This phone number is already in use");
        }

        return patientRepository.save(patient);
    }

    public void deletePatientById(Integer id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            throw new IllegalArgumentException("Patient not found");
        }

        patientRepository.delete(patientOptional.get());
    }
}
