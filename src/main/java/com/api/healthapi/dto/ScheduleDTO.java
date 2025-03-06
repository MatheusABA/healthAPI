package com.api.healthapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ScheduleDTO {
    private Integer doctor_id;
    private Integer patient_id;
    private LocalDate date;
    private String time;
    private String description;
    private String status;
}
