package com.hospital.demo.My_Hospital_Mangment_App.repository;

import com.hospital.demo.My_Hospital_Mangment_App.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface departmentRepo extends JpaRepository<Department,Long> {
}
