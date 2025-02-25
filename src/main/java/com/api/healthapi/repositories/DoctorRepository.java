package com.api.healthapi.repositories;

import com.api.healthapi.models.Doctor;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @NonNull
    Optional<Doctor> findDoctorById(Integer id);

    @NonNull
    Optional<Doctor> findDoctorByName(String name);

    @NonNull
    Optional<Doctor> findDoctorByEmail(String email);

    @NonNull
    List<Doctor> findDoctorBySpecialty(String specialty);

    @NonNull
    Optional<Doctor> findDoctorByCrm(String crm);


}
