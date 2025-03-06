package com.api.healthapi.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Schedule> schedules;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
