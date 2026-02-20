package com.movieBooking.bookyourmovie.service;

import com.movieBooking.bookyourmovie.DTO.LoginRequestDTO;
import com.movieBooking.bookyourmovie.DTO.LoginResponseDTO;
import com.movieBooking.bookyourmovie.DTO.RegisterRequestDTO;
import com.movieBooking.bookyourmovie.jwt.JwtService;
import com.movieBooking.bookyourmovie.model.User;
import com.movieBooking.bookyourmovie.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public @Nullable User registerNormalUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent())
        {
            throw new RuntimeException("User already present");
        }

        Set<String> roles=new HashSet<>();
        roles.add("ROLE_USER");

        User user=new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public @Nullable User registerAdminUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent())
        {
            throw new RuntimeException("User already present");
        }

        Set<String> roles=new HashSet<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user=new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public @Nullable LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user =userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(()->new RuntimeException("User not found"));


        String token =jwtService.generateToken(user);

        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  loginRequestDTO.getUsername(),
                    loginRequestDTO.getPassword()
          )
        );

        return  LoginResponseDTO.builder()
                .jwtToken(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }
}






























