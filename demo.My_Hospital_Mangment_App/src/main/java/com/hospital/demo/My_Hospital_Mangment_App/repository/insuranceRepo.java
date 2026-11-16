package com.hospital.demo.My_Hospital_Mangment_App.repository;

import com.hospital.demo.My_Hospital_Mangment_App.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface insuranceRepo extends JpaRepository<Insurance,Long> {

}
