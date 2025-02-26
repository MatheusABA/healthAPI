package com.api.healthapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //
    private LocalDate date;

    private String time;

    private String reason;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;
}
