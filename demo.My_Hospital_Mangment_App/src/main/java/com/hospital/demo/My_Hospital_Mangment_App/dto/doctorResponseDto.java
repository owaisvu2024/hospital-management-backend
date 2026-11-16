package com.hospital.demo.My_Hospital_Mangment_App.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class doctorResponseDto {
    private Long doctorId;
    private String doctorName;
    private String email;
    private String specialization;
    private Long maxPatientLimit;
    private String  doctorTime;
    private LocalDateTime creatAt;
}
