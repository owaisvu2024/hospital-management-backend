package com.hospital.demo.My_Hospital_Mangment_App.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardStatsDTO {
    private long patients;
    private long doctors;
    private long appointments;
    private long departments;
}

