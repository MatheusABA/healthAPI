package com.api.healthapi.repositories;

import com.api.healthapi.models.Patient;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @NonNull
    Optional<Patient> findPatientById(@NonNull Integer id);

    @NonNull
    Page<Patient> findPatientByName(String name, Pageable pageable);

    @NonNull
    Optional<Patient> findPatientByEmail(@NonNull String email);

    @NonNull
    Optional<Patient> findPatientByPhone(@NonNull String phone);

}
