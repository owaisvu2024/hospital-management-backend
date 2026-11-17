package com.hospital.demo.My_Hospital_Mangment_App.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String doctorName;
    private String email;
    private String specialization;
    private Long maxPatientLimit;
    private String  doctorTime;

    @CreationTimestamp
    private LocalDateTime creatAt;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Appointment> appointment;

    @JoinTable(
            name = "doctor_depart",
            joinColumns  = @JoinColumn(name = "doctor_Id"),
            inverseJoinColumns = @JoinColumn(name = "depart_Id")
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Department> departments = new HashSet<>();
}
