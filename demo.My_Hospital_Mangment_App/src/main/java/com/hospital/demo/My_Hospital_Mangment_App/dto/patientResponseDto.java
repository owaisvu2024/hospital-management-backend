package com.hospital.demo.My_Hospital_Mangment_App.dto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class patientResponseDto {
    private Long patientId;
    private String patientName;
    private String gender;
    private String email;
    private String bloodGroup;
    private LocalDateTime creatAt;
    private Long insuranceId;
}
