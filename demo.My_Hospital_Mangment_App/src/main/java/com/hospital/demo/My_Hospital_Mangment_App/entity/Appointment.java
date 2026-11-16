package com.hospital.demo.My_Hospital_Mangment_App.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private LocalDate appointmentDate;
    @CreationTimestamp
    private LocalDateTime creatAt;
    private String reason;
    private String status;
    private  Long tokenNumber;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "patient_id")
private Patient patient;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "doctor_id")
private Doctor doctor;
}
