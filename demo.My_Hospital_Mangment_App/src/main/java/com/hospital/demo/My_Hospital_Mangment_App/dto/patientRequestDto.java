package com.hospital.demo.My_Hospital_Mangment_App.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class patientRequestDto {
    @NotBlank(message = "PatientName is required")
    private String patientName;
    @NotBlank(message = "Gender is required")
    private String gender;
    @Email(message = "Write email correct format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "BloodGroup is required")
    private String bloodGroup;
    private Long insuranceId;
}
