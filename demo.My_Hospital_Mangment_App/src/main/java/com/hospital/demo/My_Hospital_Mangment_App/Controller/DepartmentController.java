package com.hospital.demo.My_Hospital_Mangment_App.Controller;

import com.hospital.demo.My_Hospital_Mangment_App.dto.DepartmentRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.DepartmentResponseDto;
import com.hospital.demo.My_Hospital_Mangment_App.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/post")
    public ResponseEntity<DepartmentResponseDto> add(@Valid @RequestBody DepartmentRequestDto dto){
       return ResponseEntity.ok(departmentService.add(dto));

    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','PATIENT','RECEPTIONIST')")
    @GetMapping("/get/{departId}")
    public ResponseEntity<DepartmentResponseDto> get(@PathVariable Long departId){
return ResponseEntity.ok(departmentService.get(departId));

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/patch/{departId}")
    public ResponseEntity<DepartmentResponseDto> update(@RequestBody DepartmentRequestDto dto, @PathVariable Long departId
    ) {
        return ResponseEntity.ok(departmentService.update(dto, departId));
    }
    @PreAuthorize("hasAnyRole('ADMIN','RECEPTIONIST','PATIENT','DOCTOR')")
    @GetMapping("/getall")
    public ResponseEntity <Page<DepartmentResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable= PageRequest.of(page, size);
        return ResponseEntity.ok(departmentService.getAll(pageable));

    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{departId}")
    public ResponseEntity<String>  delete(@PathVariable Long departId){

        return ResponseEntity.ok(departmentService.delete(departId));
    }

}
