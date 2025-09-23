package anjos.api_jwt.controllers;

import anjos.api_jwt.dto.jwtRequest;
import anjos.api_jwt.dto.jwtResponse;
import anjos.api_jwt.models.services.JwtValidation;
import anjos.api_jwt.models.services.JwtValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class jwtController {

    @Autowired
    private final JwtValidationService jwtValidationService;

    @Autowired
    private final JwtValidation jwtValidation;

    @PostMapping("/validJwt")
    public ResponseEntity<jwtResponse> validJwt(@RequestBody jwtRequest jwtRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(jwtValidationService.validJwt(jwtRequest.getJwt()));
    }
}
