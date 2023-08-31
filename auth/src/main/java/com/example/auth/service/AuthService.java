package com.example.auth.service;

import com.example.auth.api.OwnerClient;
import com.example.auth.client.request.OwnerRequest;
import com.example.auth.config.JwtService;
import com.example.auth.domain.entity.Role;
import com.example.auth.domain.entity.User;
import com.example.auth.domain.request.LoginRequest;
import com.example.auth.domain.request.SignupRequest;
import com.example.auth.domain.response.LoginResponse;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RestTemplate restTemplate;
    private final OwnerClient ownerClient;

    @Transactional
    public void signup(SignupRequest request) {
        User user = User.builder()
                .name(request.getName())
                .number(request.getNumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .build();

        User savedUser = userRepository.save(user);

//        ceoRestTemplate(request, savedUser);

        // 외부 서버와의 통신을 RestTemplate 대신 FeignClient를 사용하여 통신
        ceoClient(savedUser);
    }

    private void ceoRestTemplate(SignupRequest request, User savedUser) {
        if (savedUser.getRole() == Role.OWNER) {
            ResponseEntity<Void> response = restTemplate.postForEntity(
                    "http://localhost:9001/api/v1/owner",
                    new OwnerRequest(savedUser.getId(), request.getName(), request.getNumber()),
                    Void.class
            );

            if (response.getStatusCode() != HttpStatus.CREATED) {
                throw new RuntimeException(savedUser.getRole().name() + "-SERVICE DEAD");
            }
        }
    }

    private void ceoClient(User user) {
        if (user.getRole() == Role.OWNER) {
            ResponseEntity<Void> response = ownerClient.saveOwner(OwnerRequest.of(user));
            if (response.getStatusCode() != HttpStatus.CREATED) {
                throw new RuntimeException(user.getRole().name() + "-SERVICE DEAD");
            }
        }
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("NO USER FOUND"));

        validatePassword(request, user);

        String token = jwtService.generateToken(user);

        return LoginResponse.of(token, user.getRole().name());
    }

    private void validatePassword(LoginRequest request, User user) {
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("PASSWORD NOT MATCHED");
        }
    }

}
