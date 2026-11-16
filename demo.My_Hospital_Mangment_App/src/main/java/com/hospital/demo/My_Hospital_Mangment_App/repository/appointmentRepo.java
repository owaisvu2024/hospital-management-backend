package com.hospital.demo.My_Hospital_Mangment_App.repository;

import com.hospital.demo.My_Hospital_Mangment_App.entity.Appointment;
import com.hospital.demo.My_Hospital_Mangment_App.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface appointmentRepo extends JpaRepository<Appointment,Long> {
    Long countByDoctorAndAppointmentDate(Doctor doctor, LocalDate appointmentDate);

}

