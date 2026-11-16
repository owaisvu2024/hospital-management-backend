package com.hospital.demo.My_Hospital_Mangment_App.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AppointmentRequestDto {

    @NotNull(message = "AppointmentDate is required")
    private LocalDate appointmentDate;
    @NotBlank(message = " Reason is required")
    private String reason;
    @NotBlank(message = " status is required")
    private String status;
    @NotNull(message = "PatientId is required")
    private Long patientId;
    @NotNull(message = "DoctorId is required")
    private Long doctorId;

}
