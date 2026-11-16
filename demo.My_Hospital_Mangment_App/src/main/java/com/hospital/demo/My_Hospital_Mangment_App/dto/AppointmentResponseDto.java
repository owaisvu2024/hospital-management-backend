package com.hospital.demo.My_Hospital_Mangment_App.dto;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AppointmentResponseDto {

    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalDateTime creatAt;
    private String reason;
    private String status;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private Long tokenNumber;

}
