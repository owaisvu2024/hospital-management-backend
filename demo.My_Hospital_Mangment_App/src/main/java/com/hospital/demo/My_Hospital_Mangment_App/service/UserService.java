package com.hospital.demo.My_Hospital_Mangment_App.service;

import com.hospital.demo.My_Hospital_Mangment_App.Security.JwtUtil;
import com.hospital.demo.My_Hospital_Mangment_App.dto.LoginRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.SignupRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.entity.Role;
import com.hospital.demo.My_Hospital_Mangment_App.entity.User;
import com.hospital.demo.My_Hospital_Mangment_App.errorHandling.ResourceNotFoundException;
import com.hospital.demo.My_Hospital_Mangment_App.repository.AuthRepo;
import com.hospital.demo.My_Hospital_Mangment_App.repository.doctorRepo;
import com.hospital.demo.My_Hospital_Mangment_App.repository.patientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private AuthRepo  authRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private patientRepo patientRepo;
    @Autowired
    private doctorRepo doctorRepo;


    public User signup(SignupRequestDto Dto) {
        User user = new User();
       if (authRepo.existsByUserName(Dto.getUserName())) {
           throw new ResourceNotFoundException("Username already exists");
       }
        user.setUserName(Dto.getUserName());
        user.setPassword(passwordEncoder.encode(Dto.getPassword()));
        if(Dto.getRoles()==null || Dto.getRoles().isEmpty()){
            user.setRoles(Set.of(Role.PATIENT));
        }else {
            user.setRoles(Dto.getRoles());
        }



        return authRepo.save(user);

    }

    public String Login(LoginRequestDto Dto) {

        User user1=authRepo.findByUserName(Dto.getUserName());
        if (user1==null) {
            throw new ResourceNotFoundException("Username not found");
        }
        boolean Password = passwordEncoder.matches(Dto.getPassword(),user1.getPassword());
        if (!Password) {
            throw new ResourceNotFoundException("Password not match");
        }

return jwtUtil.generateToken(user1.getUserName(), user1.getRoles());

    }

    public void registerUserByAdmin(SignupRequestDto dto) {
        // 1. Check karo ke username pehle se toh nahi hai
        if (authRepo.existsByUserName(dto.getUserName())) {
            throw new RuntimeException("Username already exists!");
        }

        // 2. User table ke liye save karo (Password encode karke)
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // 🔑 Secure Hash
        user.setRoles(dto.getRoles()); // Admin jo bhi role bhejega (DOCTOR, PATIENT, etc.)
        authRepo.save(user);


    }
}
