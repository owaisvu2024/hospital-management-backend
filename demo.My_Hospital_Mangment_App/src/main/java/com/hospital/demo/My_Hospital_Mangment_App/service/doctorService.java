package com.hospital.demo.My_Hospital_Mangment_App.service;

import com.hospital.demo.My_Hospital_Mangment_App.dto.doctorRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.doctorResponseDto;
import com.hospital.demo.My_Hospital_Mangment_App.entity.Doctor;
import com.hospital.demo.My_Hospital_Mangment_App.errorHandling.ResourceNotFoundException;
import com.hospital.demo.My_Hospital_Mangment_App.repository.doctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class doctorService {
    @Autowired
    private doctorRepo repo;


    public doctorResponseDto add(doctorRequestDto dto){

        Doctor doctor=new Doctor();
        if (repo.existsByEmail(dto.getEmail())){
            throw new ResourceNotFoundException("Email already exists");
        };

        doctor.setDoctorName(dto.getDoctorName());
        doctor.setEmail(dto.getEmail());
        doctor.setDoctorTime(dto.getDoctorTime());
        doctor.setMaxPatientLimit(dto.getMaxPatientLimit());
       doctor.setSpecialization(dto.getSpecialization());
       Doctor saved=repo.save(doctor);
       return convertDto(saved);
    }

    public String  delete(Long doctorId){
        Doctor doctor=repo.findById(doctorId).
                orElseThrow(()->new ResourceNotFoundException("Doctor id not found"));
        repo.delete(doctor);
        return "Delete successfully";

    }

    public doctorResponseDto get(Long doctorId){
        Doctor doctor=repo.findById(doctorId).
                orElseThrow(()->new ResourceNotFoundException("Doctor id not found"));

        return convertDto(doctor);
    }
    public Page<doctorResponseDto> getAll(Pageable pageable){
        Page<Doctor> doctor=repo.findAll(pageable);

        return doctor.map(entity->convertDto(entity));
    }

    public doctorResponseDto update(doctorRequestDto dto,Long doctorId){
        Doctor doctor=repo.findById(doctorId).
                orElseThrow(()->new ResourceNotFoundException("Doctor id not found"));

        if (dto.getDoctorName()!=null){
            doctor.setDoctorName(dto.getDoctorName());
        }
        if (dto.getDoctorTime()!=null){
            doctor.setDoctorTime(dto.getDoctorTime());
        }
        if (dto.getEmail()!=null){
            doctor.setEmail(dto.getEmail());
        }
        if (dto.getMaxPatientLimit()!=null){
            doctor.setMaxPatientLimit(dto.getMaxPatientLimit());
        }
        if (dto.getSpecialization()!=null){
            doctor.setSpecialization(dto.getSpecialization());
        }
        return convertDto(repo.save(doctor));
    }
    public doctorResponseDto convertDto(Doctor entity){
        doctorResponseDto dto=new doctorResponseDto();
        dto.setCreatAt(entity.getCreatAt());
        dto.setDoctorId(entity.getDoctorId());
        dto.setDoctorName(entity.getDoctorName());
        dto.setDoctorTime(entity.getDoctorTime());
        dto.setEmail(entity.getEmail());
        dto.setMaxPatientLimit(entity.getMaxPatientLimit());
        dto.setSpecialization(entity.getSpecialization());
        return dto;

    }

}
