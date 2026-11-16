package com.hospital.demo.My_Hospital_Mangment_App.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank(message = "UserName is required")
    private String userName;
    @NotBlank(message = "Password is required")
    private String password;
}
