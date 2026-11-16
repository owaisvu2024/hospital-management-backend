package com.hospital.demo.My_Hospital_Mangment_App.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long patientId;
private String patientName;
private String gender;
private String email;
private String bloodGroup;


@CreationTimestamp
private LocalDateTime creatAt;


@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "insurance_id")
    private Insurance insurance;

@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Appointment> appointment;
}
