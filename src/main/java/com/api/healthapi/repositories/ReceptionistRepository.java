package com.api.healthapi.repositories;

import com.api.healthapi.models.Receptionist;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Integer> {

    @NonNull
    Optional<Receptionist> findReceptionistById(@NonNull Integer id);

    @NonNull
    Optional<Receptionist> findReceptionistByName(String name);

    @NonNull
    Optional<Receptionist> findReceptionistByEmail(String email);







}
