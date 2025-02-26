package com.api.healthapi.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String specialty;

    @Column(unique = true)
    private String crm;

    @Column(unique = true)
    private String email;

    private String phone;

    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
