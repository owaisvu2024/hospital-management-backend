package com.hospital.demo.My_Hospital_Mangment_App.Controller;

import com.hospital.demo.My_Hospital_Mangment_App.dto.doctorRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.doctorResponseDto;
import com.hospital.demo.My_Hospital_Mangment_App.service.doctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
public class doctorController {
    @Autowired
    private doctorService doctorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/post")
    public ResponseEntity<doctorResponseDto>add(@Valid @RequestBody doctorRequestDto dto){
        return ResponseEntity.ok(doctorService.add(dto));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<String>  delete(@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorService.delete(doctorId));

    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST','DOCTOR')")
    @GetMapping("/get/{doctorId}")
    public ResponseEntity<doctorResponseDto> get(@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorService.get(doctorId));
    }
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST','DOCTOR')")
    @GetMapping("/getall")
    public ResponseEntity <Page<doctorResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size

    ){
        Pageable pageable= PageRequest.of(page,size);
        return ResponseEntity.ok(doctorService.getAll(pageable));


    }
    @PreAuthorize("hasRole('ADMIN')")

    @PatchMapping("/patch/{doctorId}")
    public ResponseEntity<doctorResponseDto> update(@RequestBody doctorRequestDto dto,@PathVariable Long doctorId){

        return ResponseEntity.ok(doctorService.update(dto,doctorId));
    }

}
