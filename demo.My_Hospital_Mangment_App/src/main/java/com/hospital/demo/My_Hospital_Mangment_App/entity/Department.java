package com.hospital.demo.My_Hospital_Mangment_App.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long departId;
private String  departName;
@CreationTimestamp
private LocalDateTime creatAt;


@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "Doctor_Head_Id")
private Doctor doctor;
@ManyToMany(mappedBy = "departments", fetch = FetchType.LAZY)
private List<Doctor> doctors;
}
