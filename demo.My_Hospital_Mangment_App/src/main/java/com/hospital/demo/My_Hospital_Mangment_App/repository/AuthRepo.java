package com.hospital.demo.My_Hospital_Mangment_App.repository;

import com.hospital.demo.My_Hospital_Mangment_App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<User,Long> {
boolean existsByUserName(String userName);
User findByUserName(String userName);
}
