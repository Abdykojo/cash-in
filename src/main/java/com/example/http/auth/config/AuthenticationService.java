package com.example.http.auth.config;

import com.example.http.commons.Exeptions.AuthenticationExeption;
import com.example.http.commons.Exeptions.RegisterExeption;
import com.example.http.commons.RespMessage;
import com.example.http.entity.Role;
import com.example.http.entity.User;
import com.example.http.repository.UserRepository;
import com.example.http.request.AuthenticationRequest;
import com.example.http.request.RegisterRequest;
import com.example.http.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) throws RegisterExeption {

        log.info("Registration -> {}", request);

        try {

            var user = User.builder()
                    .name(request.getName())
                    .birthdate(request.getBirthdate())
                    .phoneNumber(request.getPhone())
                    .last_name(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            repository.save(user);

            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();

        }catch (RegisterExeption e) {
             throw new RegisterExeption(RespMessage.NOT_REGISTER.getMessage());
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationExeption{

        log.info("Authenticate -> {}", request);

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow(()->new UsernameNotFoundException(RespMessage.USER_NOT_FOUND.getMessage()));
            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();

        }catch (AuthenticationExeption e) {

            throw new AuthenticationExeption(RespMessage.NOT_AUTH.getMessage());
        }
    }
}
