package com.api.healthapi.repositories;

import com.api.healthapi.models.Patient;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @NonNull
    Optional<Patient> findPatientById(@NonNull Integer id);

    @NonNull
    Optional<Patient> findPatientByName(String name);

    @NonNull
    Optional<Patient> findPatientByEmail(@NonNull String email);

    @NonNull
    Optional<Patient> findPatientByPhone(@NonNull String phone);

}
