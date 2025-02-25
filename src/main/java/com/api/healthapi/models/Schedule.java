package com.api.healthapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;

    private String time;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;
}
