package com.hospital.demo.My_Hospital_Mangment_App.repository;

import com.hospital.demo.My_Hospital_Mangment_App.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface patientRepo extends JpaRepository<Patient,Long> {
boolean existsByEmail(String email);
}
