package com.hospital.demo.My_Hospital_Mangment_App.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentRequestDto {
    @NotBlank(message = "Department Name is required")
    private String  departName;
    private Long doctorId;

}
