package com.hospital.demo.My_Hospital_Mangment_App.Controller;

import com.hospital.demo.My_Hospital_Mangment_App.dto.patientRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.patientResponseDto;
import com.hospital.demo.My_Hospital_Mangment_App.service.AppointmentService;
import com.hospital.demo.My_Hospital_Mangment_App.service.patientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class patientController {
    @Autowired
    private patientService patientService;
    @Autowired
    private AppointmentService appointmentService;


    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST')")
    @PostMapping("/post")
    public ResponseEntity<patientResponseDto> add(@Valid @RequestBody patientRequestDto dto) {
        return ResponseEntity.ok(patientService.add(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST','DOCTOR')")
    @GetMapping("/get/{patientId}")
    public ResponseEntity<patientResponseDto> get(@PathVariable Long patientId){

    return ResponseEntity.ok(patientService.get(patientId));

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<String> delete(@PathVariable Long patientId){

        return ResponseEntity.ok(patientService.delete(patientId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST')")
    @GetMapping("/getall")
    public ResponseEntity<Page<patientResponseDto>> getAll(
    @RequestParam(defaultValue = "0")int page,
    @RequestParam(defaultValue = "10")int  size

    ) {
       Pageable pageable= PageRequest.of(page,size);
       return ResponseEntity.ok(patientService.getAll(pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST')")
    @PatchMapping("/patch/{patientId}")
    public ResponseEntity<patientResponseDto> update(@RequestBody patientRequestDto dto,@PathVariable Long patientId){

        return ResponseEntity.ok(patientService.update(dto,patientId));
    }

}
