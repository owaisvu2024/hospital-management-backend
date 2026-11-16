package com.hospital.demo.My_Hospital_Mangment_App.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class doctorRequestDto {
    @NotBlank(message = "Doctor name is required")
    private String doctorName;
    @Email(message = "Email enter correct format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Specialization is required")
    private String specialization;
    @NotNull(message = "PatientLimit is required")
    private Long maxPatientLimit;
    @NotBlank(message = "DoctorTime  is required")
    private String  doctorTime;

}
