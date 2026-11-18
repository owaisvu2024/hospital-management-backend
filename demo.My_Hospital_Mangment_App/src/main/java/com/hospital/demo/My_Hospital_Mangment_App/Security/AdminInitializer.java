//package com.hospital.demo.My_Hospital_Mangment_App.Security; // Agar aapka package naam alag hai toh apne hisab se adjust kar lein
//
//import com.hospital.demo.My_Hospital_Mangment_App.entity.Role;
//import com.hospital.demo.My_Hospital_Mangment_App.entity.User;
//import com.hospital.demo.My_Hospital_Mangment_App.repository.AuthRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Set;
//
//@Component
//public class AdminInitializer implements CommandLineRunner {
//
//    @Autowired
//    private AuthRepo authRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Check karo ke kya 'admin' naam ka user pehle se mojood hai?
//        User existingAdmin = authRepo.findByUserName("admin");
//
//        if (existingAdmin == null) {
//            User admin = new User();
//            admin.setUserName("admin"); // Default Username
//            admin.setPassword(passwordEncoder.encode("admin123")); // Password khud encode ho jayega
//            admin.setRoles(Set.of(Role.ADMIN)); // Role ADMIN assign ho gaya
//
//            authRepo.save(admin);
//            System.out.println("🌟 Default Admin account created automatically: admin / admin123");
//        }
//    }
//}