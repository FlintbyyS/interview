package ru.flint.interview.web.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.flint.interview.service.AuthenticationService;
import ru.flint.interview.web.dto.request.AuthenticationRequest;
import ru.flint.interview.web.dto.request.RegisterRequest;
import ru.flint.interview.web.dto.response.AuthenticationResponse;

import java.io.IOException;

@RestController
@RequestMapping(value = AuthenticationController.REST_URL)
@RequiredArgsConstructor
public class AuthenticationController {
    public static final String REST_URL = "/api/version1.0/auth";
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
