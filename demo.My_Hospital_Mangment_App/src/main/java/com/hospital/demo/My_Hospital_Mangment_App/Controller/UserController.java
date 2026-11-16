package com.hospital.demo.My_Hospital_Mangment_App.Controller;

import com.hospital.demo.My_Hospital_Mangment_App.dto.LoginRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.dto.SignupRequestDto;
import com.hospital.demo.My_Hospital_Mangment_App.entity.User;
import com.hospital.demo.My_Hospital_Mangment_App.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody SignupRequestDto Dto) {

          return ResponseEntity.ok(userService.signup(Dto));
    }

    @PostMapping("/login")
    public String Login(@Valid @RequestBody LoginRequestDto Dto) {

        return userService.Login(Dto);

    }


}
