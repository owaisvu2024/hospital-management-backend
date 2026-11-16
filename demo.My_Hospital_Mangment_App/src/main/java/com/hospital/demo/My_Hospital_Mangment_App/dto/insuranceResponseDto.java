package com.hospital.demo.My_Hospital_Mangment_App.dto;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class insuranceResponseDto {
    private Long insuranceId;
    private String policyNumber;
    private String provider;
    private LocalDate validUntil;

    private LocalDateTime creatAt;
}
