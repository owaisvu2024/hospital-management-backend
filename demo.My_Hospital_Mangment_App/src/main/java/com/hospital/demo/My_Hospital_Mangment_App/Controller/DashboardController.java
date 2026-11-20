package com.hospital.demo.My_Hospital_Mangment_App.Controller;
import com.hospital.demo.My_Hospital_Mangment_App.dto.DashboardStatsDTO;
import com.hospital.demo.My_Hospital_Mangment_App.repository.appointmentRepo;
import com.hospital.demo.My_Hospital_Mangment_App.repository.departmentRepo;
import com.hospital.demo.My_Hospital_Mangment_App.repository.doctorRepo;
import com.hospital.demo.My_Hospital_Mangment_App.repository.patientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final patientRepo patientRepository;
    private final doctorRepo doctorRepository;
    private final appointmentRepo appointmentRepository;
    private final departmentRepo departmentRepository;

    @GetMapping("/stats")
    public DashboardStatsDTO getStats() {
        return new DashboardStatsDTO(
                patientRepository.count(),
                doctorRepository.count(),
                appointmentRepository.count(),
                departmentRepository.count()
        );
    }
}
