package com.movieBooking.bookyourmovie.controller;


import com.movieBooking.bookyourmovie.DTO.LoginRequestDTO;
import com.movieBooking.bookyourmovie.DTO.LoginResponseDTO;
import com.movieBooking.bookyourmovie.DTO.RegisterRequestDTO;
import com.movieBooking.bookyourmovie.model.User;
import com.movieBooking.bookyourmovie.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationService authencationService;

    @PostMapping("/registernormaluser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO)
    {
        return ResponseEntity.ok(authencationService.registerNormalUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO)
    {
        return ResponseEntity.ok(authencationService.login(loginRequestDTO));
    }

}









