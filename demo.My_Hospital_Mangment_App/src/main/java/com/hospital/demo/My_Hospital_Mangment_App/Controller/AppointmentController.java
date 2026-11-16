package com.hospital.demo.My_Hospital_Mangment_App.Controller;

import com.hospital.demo.My_Hospital_Mangment_App.dto.AppointmentRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.AppointmentResponseDto;
import com.hospital.demo.My_Hospital_Mangment_App.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST')")
    @PostMapping("/post")
    public ResponseEntity<AppointmentResponseDto> add(@Valid @RequestBody AppointmentRequestDto dto){

       return  ResponseEntity.ok(appointmentService.add(dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST')")
    @GetMapping("/get/{appointmentId}")
    public ResponseEntity<AppointmentResponseDto> get(@PathVariable Long appointmentId){
      return ResponseEntity.ok(appointmentService.get(appointmentId));

    }
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST','DOCTOR')")
    @GetMapping("/getall")
    public ResponseEntity<Page<AppointmentResponseDto>> getAll(
            @RequestParam(defaultValue = "0")int page,
             @RequestParam(defaultValue = "5")int size
    ){
        Pageable pageable= PageRequest.of(page, size);
        return ResponseEntity.ok(appointmentService.getAll(pageable));

    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<String> delete(@PathVariable Long appointmentId){
       return ResponseEntity.ok(appointmentService.delete(appointmentId));
    }
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST')")
    @PatchMapping("/patch/{appointmentId}")
    public ResponseEntity<AppointmentResponseDto> update(@RequestBody AppointmentRequestDto dto,@PathVariable Long appointmentId){
        return ResponseEntity.ok(appointmentService.update(dto,appointmentId));

}

}
