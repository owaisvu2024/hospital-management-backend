package com.hospital.demo.My_Hospital_Mangment_App.service;

import com.hospital.demo.My_Hospital_Mangment_App.dto.DepartmentRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.DepartmentResponseDto;
import com.hospital.demo.My_Hospital_Mangment_App.entity.Department;
import com.hospital.demo.My_Hospital_Mangment_App.entity.Doctor;
import com.hospital.demo.My_Hospital_Mangment_App.errorHandling.ResourceNotFoundException;
import com.hospital.demo.My_Hospital_Mangment_App.repository.departmentRepo;
import com.hospital.demo.My_Hospital_Mangment_App.repository.doctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private departmentRepo departmentRepo;
    @Autowired
    private doctorRepo doctorRepo;

    public DepartmentResponseDto add(DepartmentRequestDto dto){
        Department department=new Department();
        department.setDepartName(dto.getDepartName());
        if (dto.getDoctorId()!=null){
            Doctor doctor=doctorRepo.findById(dto.getDoctorId()).
                    orElseThrow(()->new ResourceNotFoundException("Doctor Id not found"));
            department.setDoctor(doctor);
        }
        return convertDto(departmentRepo.save(department));

    }
    public DepartmentResponseDto get(Long departId){

            Department department=departmentRepo.findById(departId).
                    orElseThrow(()->new ResourceNotFoundException("Department Id not found"));

            return convertDto(department);

    }
    public Page<DepartmentResponseDto> getAll(Pageable pageable){
        Page<Department> pageable1=departmentRepo.findAll(pageable);
        return pageable1.map(entity->convertDto(entity));



    }
    public DepartmentResponseDto update(DepartmentRequestDto dto, Long departId) {
        Department department = departmentRepo.findById(departId)
                .orElseThrow(() -> new ResourceNotFoundException("Department Id not found "));

        if (dto.getDepartName() != null) {
            department.setDepartName(dto.getDepartName());
        }
        if (dto.getDoctorId() != null) {
            Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor Id not found"));
            department.setDoctor(doctor);
        }
        return convertDto(departmentRepo.save(department));
    }
    public String  delete(Long departId){

        Department department=departmentRepo.findById(departId).
                orElseThrow(()->new ResourceNotFoundException("Department Id not found"));

        departmentRepo.delete(department);
        return "Delete successfully";
    }

    private DepartmentResponseDto convertDto(Department entity){
        DepartmentResponseDto dto=new DepartmentResponseDto();
        dto.setDepartId(entity.getDepartId());
        dto.setDepartName(entity.getDepartName());
        dto.setCreatAt(entity.getCreatAt());
        if (entity.getDoctor()!=null){
            dto.setDoctorHeadId(entity.getDoctor().getDoctorId());
        }

        return dto;
    }
}
