package com.hospital.demo.My_Hospital_Mangment_App.Controller;

import com.hospital.demo.My_Hospital_Mangment_App.dto.insuranceRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.insuranceResponseDto;
import com.hospital.demo.My_Hospital_Mangment_App.service.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/insurance")
public class insuranceController {
    @Autowired
    private InsuranceService service;
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/post")
    public ResponseEntity<insuranceResponseDto> Add(@Valid @RequestBody insuranceRequestDto requestDto){

        return new ResponseEntity<>(service.Add(requestDto), HttpStatus.CREATED) ;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{insuranceId}")
    public ResponseEntity<String>  delete(@PathVariable Long insuranceId){
        return ResponseEntity.ok(service.delete(insuranceId));

    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping("/update/{insuranceId}")
    public ResponseEntity<insuranceResponseDto> update(@RequestBody insuranceRequestDto dto,@PathVariable Long insuranceId){

        return ResponseEntity.ok(service.update(dto,insuranceId));

    }
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST','DOCTOR')")
    @GetMapping("/getall")
    public ResponseEntity<Page<insuranceResponseDto>> getAll(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable= PageRequest.of(page,size);
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','RECEPTIONIST','DOCTOR')")
    @GetMapping("/get/{insuranceId}")
    public ResponseEntity<insuranceResponseDto> get(@PathVariable Long insuranceId){
        return ResponseEntity.ok(service.get(insuranceId));

    }

}
