package com.hospital.demo.My_Hospital_Mangment_App.service;

import com.hospital.demo.My_Hospital_Mangment_App.dto.patientRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.patientResponseDto;
import com.hospital.demo.My_Hospital_Mangment_App.entity.Insurance;
import com.hospital.demo.My_Hospital_Mangment_App.entity.Patient;
import com.hospital.demo.My_Hospital_Mangment_App.errorHandling.ResourceNotFoundException;
import com.hospital.demo.My_Hospital_Mangment_App.repository.insuranceRepo;
import com.hospital.demo.My_Hospital_Mangment_App.repository.patientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class patientService {

    @Autowired
    private patientRepo patientRepo;
    @Autowired
    private insuranceRepo insuranceRepo;
    public patientResponseDto add(patientRequestDto dto) {
        if (patientRepo.existsByEmail(dto.getEmail())) {
            throw new ResourceNotFoundException("Email already exists");
        }

        Patient patient = new Patient();
        patient.setPatientName(dto.getPatientName());
        patient.setGender(dto.getGender());
        patient.setEmail(dto.getEmail());
        patient.setBloodGroup(dto.getBloodGroup());

        if (dto.getInsuranceId() != null) {
            Insurance insurance = insuranceRepo.findById(dto.getInsuranceId())
                    .orElseThrow(() -> new ResourceNotFoundException("Insurance Id not found"));
            patient.setInsurance(insurance);
        }

        Patient savedPatient = patientRepo.save(patient);
        return convertDto(savedPatient);
    }

    public patientResponseDto get(Long patientId){
        Patient patient=patientRepo.findById(patientId).
                orElseThrow(()->new ResourceNotFoundException("Patient Id not found "));

        return convertDto(patient);
    }
    public String delete(Long patientId){
        Patient patient=patientRepo.findById(patientId).
                orElseThrow(()->new ResourceNotFoundException("Patient Id not found "));

       patientRepo.delete(patient);
      return "Delete succesfully";
    }

    public Page<patientResponseDto> getAll(Pageable pageable) {
       Page< Patient> patientPage=patientRepo.findAll(pageable);

       return  patientPage.map(entity->convertDto(entity));
    }
    public patientResponseDto update(patientRequestDto dto,Long patientId){
        Patient patient=patientRepo.findById(patientId).
                orElseThrow(()->new ResourceNotFoundException("Patient Id not found"));

        if (dto.getBloodGroup()!=null){
            patient.setBloodGroup(dto.getBloodGroup());
        }
        if (dto.getEmail()!=null){
            patient.setEmail(dto.getEmail());
        }
        if (dto.getInsuranceId()!=null){
            Insurance insurance=insuranceRepo.findById(dto.getInsuranceId()).
                    orElseThrow(()->new ResourceNotFoundException("Insurance Id not found"));
            patient.setInsurance(insurance);
        }
        if (dto.getGender()!=null){
            patient.setGender(dto.getGender());
        }
        if (dto.getPatientName()!=null){
            patient.setPatientName(dto.getPatientName());
        }
        return convertDto(patientRepo.save(patient));
    }
    private patientResponseDto convertDto(Patient entity){
   patientResponseDto dto=new patientResponseDto();
   dto.setPatientId(entity.getPatientId());
   dto.setPatientName(entity.getPatientName());
   dto.setGender(entity.getGender());
   dto.setBloodGroup(entity.getBloodGroup());
   dto.setCreatAt(entity.getCreatAt());
   dto.setEmail(entity.getEmail());
   if (entity.getInsurance()!=null){
       dto.setInsuranceId(entity.getInsurance().getInsuranceId());
   }
   return dto;

    }
}
