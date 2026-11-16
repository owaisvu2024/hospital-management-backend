package com.hospital.demo.My_Hospital_Mangment_App.dto;

import com.hospital.demo.My_Hospital_Mangment_App.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequestDto {
    @NotBlank(message = "UserName is required")
    private String userName;
    @NotBlank(message = "Password is required")
    private String password;
    private Set<Role> roles;

}
