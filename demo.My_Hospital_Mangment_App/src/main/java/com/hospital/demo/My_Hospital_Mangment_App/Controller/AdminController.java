package com.hospital.demo.My_Hospital_Mangment_App.Controller;

import com.hospital.demo.My_Hospital_Mangment_App.dto.SignupRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    // Sirf Admin hi is endpoint ko call kar sakega
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-user")
    public ResponseEntity<String> createUserByAdmin(@RequestBody SignupRequestDto dto) {
        userService.registerUserByAdmin(dto);
        return ResponseEntity.ok("User (Doctor/Staff/Patient) successfully added by Admin!");
    }
}