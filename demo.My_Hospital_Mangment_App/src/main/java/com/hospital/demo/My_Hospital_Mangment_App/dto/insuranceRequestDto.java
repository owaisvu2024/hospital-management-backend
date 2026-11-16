package com.hospital.demo.My_Hospital_Mangment_App.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class insuranceRequestDto {
    @NotBlank(message = "Enter policyNumber")
    private String policyNumber;
    @NotBlank(message = "Enter Provider")
    private String provider;
   @NotNull(message = "Enter validUntil")
    private LocalDate validUntil;
}
