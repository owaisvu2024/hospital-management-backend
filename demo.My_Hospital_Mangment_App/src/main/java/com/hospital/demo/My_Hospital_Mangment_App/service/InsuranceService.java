package com.hospital.demo.My_Hospital_Mangment_App.service;

import com.hospital.demo.My_Hospital_Mangment_App.dto.insuranceRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.insuranceResponseDto;
import com.hospital.demo.My_Hospital_Mangment_App.entity.Insurance;
import com.hospital.demo.My_Hospital_Mangment_App.errorHandling.ResourceNotFoundException;
import com.hospital.demo.My_Hospital_Mangment_App.repository.insuranceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class InsuranceService {
    @Autowired
    private insuranceRepo insuranceRepo;

    public insuranceResponseDto Add(insuranceRequestDto requestDto){
        Insurance insurance=new Insurance();
        insurance.setPolicyNumber(requestDto.getPolicyNumber());
        insurance.setProvider(requestDto.getProvider());
        insurance.setValidUntil(requestDto.getValidUntil());
        Insurance insurance1= insuranceRepo.save(insurance);
        return convertDto(insurance1);
    }
    public String  delete(Long insuranceId){
        Insurance insurance=insuranceRepo.findById(insuranceId).
                orElseThrow(()->new ResourceNotFoundException("Insurance not found with id: "+insuranceId));

        insuranceRepo.delete(insurance);
        return "Delete Successfully";
    }

    public insuranceResponseDto update(insuranceRequestDto dto,Long insuranceId){
        Insurance insurance=insuranceRepo.findById(insuranceId).
                orElseThrow(()->new ResourceNotFoundException("Insurance Not found"));

        if (dto.getPolicyNumber()!=null){
            insurance.setPolicyNumber(dto.getPolicyNumber());
        }
        if (dto.getProvider()!=null){
            insurance.setProvider(dto.getProvider());

        }
        if (dto.getValidUntil()!=null){
            insurance.setValidUntil(dto.getValidUntil());
        }
        Insurance saved=insuranceRepo.save(insurance);
        return convertDto(saved);

    }

    public Page<insuranceResponseDto> getAll(Pageable pageable){

        Page<Insurance> insurancePage=insuranceRepo.findAll(pageable);



        return insurancePage.map(entity->convertDto(entity));

    }
    public insuranceResponseDto get(Long insuranceId){
        Insurance insurance=insuranceRepo.findById(insuranceId).
                orElseThrow(()->new ResourceNotFoundException("Insurance Not Found"));

        return convertDto(insurance);

    }
public insuranceResponseDto convertDto(Insurance insurance){
        insuranceResponseDto dto=new insuranceResponseDto();
        dto.setInsuranceId(insurance.getInsuranceId());
        dto.setPolicyNumber(insurance.getPolicyNumber());
        dto.setProvider(insurance.getProvider());
        dto.setValidUntil(insurance.getValidUntil());
        dto.setCreatAt(insurance.getCreatAt());
        return dto;
}
}
