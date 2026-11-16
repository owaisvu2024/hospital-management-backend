package com.hospital.demo.My_Hospital_Mangment_App.service;

import com.hospital.demo.My_Hospital_Mangment_App.dto.AppointmentRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.AppointmentResponseDto;
import com.hospital.demo.My_Hospital_Mangment_App.entity.Appointment;
import com.hospital.demo.My_Hospital_Mangment_App.entity.Doctor;
import com.hospital.demo.My_Hospital_Mangment_App.entity.Patient;
import com.hospital.demo.My_Hospital_Mangment_App.errorHandling.ResourceNotFoundException;
import com.hospital.demo.My_Hospital_Mangment_App.repository.AuthRepo;
import com.hospital.demo.My_Hospital_Mangment_App.repository.appointmentRepo;
import com.hospital.demo.My_Hospital_Mangment_App.repository.doctorRepo;
import com.hospital.demo.My_Hospital_Mangment_App.repository.patientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    private appointmentRepo appointmentRepo;
    @Autowired
    private patientRepo patientRepo;
    @Autowired
    private doctorRepo doctorRepo;
    @Autowired
    private AuthRepo authRepo;

    public AppointmentResponseDto add(AppointmentRequestDto dto){
        Appointment appointment=new Appointment();

        Patient patient=patientRepo.findById(dto.getPatientId()).
                orElseThrow(()->new ResourceNotFoundException("Patient not found"));
        Doctor doctor=doctorRepo.findById(dto.getDoctorId()).
                orElseThrow(()->new ResourceNotFoundException("doctor not found"));

        Long Booked=appointmentRepo.countByDoctorAndAppointmentDate( doctor,  dto.getAppointmentDate());

        if (Booked>=doctor.getMaxPatientLimit()){
            throw new RuntimeException("Doctor's limit for this day is full.");
        }
        appointment.setAppointmentDate(dto.getAppointmentDate());
       appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setReason(dto.getReason());
        appointment.setStatus(dto.getStatus());
        appointment.setTokenNumber((Long)Booked+1);
      return convertDto(appointmentRepo.save(appointment));
    }
    public AppointmentResponseDto get(Long appointmentId){
        Appointment appointment=appointmentRepo.findById(appointmentId).
                orElseThrow(()->new ResourceNotFoundException("Appointment not found"));

        return convertDto(appointment);

    }
    public Page<AppointmentResponseDto> getAll(Pageable pageable){
        Page<Appointment> appointment=appointmentRepo.findAll(pageable);
        return appointment.map(entity->convertDto(entity));
    }
    public String delete(Long appointmentId){
        Appointment appointment=appointmentRepo.findById(appointmentId).
                orElseThrow(()->new ResourceNotFoundException("Appointment not found"));
        appointmentRepo.delete(appointment);
        return "Delete successfully";
    }
    public AppointmentResponseDto update(AppointmentRequestDto dto,Long appointmentId){
        Appointment appointment=appointmentRepo.findById(appointmentId).
                orElseThrow(()->new ResourceNotFoundException("Appointment not found"));

        if (dto.getAppointmentDate()!=null){
            appointment.setAppointmentDate(dto.getAppointmentDate());

        }
        if (dto.getDoctorId()!=null){
            Doctor doctor=doctorRepo.findById(dto.getDoctorId()).
                    orElseThrow(()->new ResourceNotFoundException("Doctor not found"));

           appointment.setDoctor(doctor);

        }
        if (dto.getPatientId()!=null){
            Patient patient=patientRepo.findById(dto.getPatientId()).
                    orElseThrow(()->new ResourceNotFoundException("Patient not found"));

            appointment.setPatient(patient);

        }
        if (dto.getReason()!=null){
            appointment.setReason(dto.getReason());
        }
        if (dto.getStatus()!=null){
            appointment.setStatus(dto.getStatus());
        }
        return convertDto(appointmentRepo.save(appointment));

    }




    private AppointmentResponseDto convertDto(Appointment entity){
        AppointmentResponseDto dto=new AppointmentResponseDto();

        dto.setAppointmentId(entity.getAppointmentId());
        dto.setAppointmentDate(entity.getAppointmentDate());
        dto.setCreatAt(entity.getCreatAt());
        dto.setReason(entity.getReason());
        dto.setStatus(entity.getStatus());
        dto.setDoctorId(entity.getDoctor().getDoctorId());
        dto.setDoctorName(entity.getDoctor().getDoctorName());
        dto.setPatientId(entity.getPatient().getPatientId());
        dto.setPatientName(entity.getPatient().getPatientName());
        dto.setTokenNumber(entity.getTokenNumber());
        return dto;

    }


}
