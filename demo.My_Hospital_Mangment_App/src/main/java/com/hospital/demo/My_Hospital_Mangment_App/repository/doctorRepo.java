package com.hospital.demo.My_Hospital_Mangment_App.repository;

import com.hospital.demo.My_Hospital_Mangment_App.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface doctorRepo extends JpaRepository<Doctor,Long> {
    boolean existsByEmail(String email);

}
