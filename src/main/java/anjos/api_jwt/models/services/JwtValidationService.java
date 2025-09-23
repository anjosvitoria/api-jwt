package anjos.api_jwt.models.services;

import anjos.api_jwt.dto.jwtResponse;
import anjos.api_jwt.models.enums.statics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtValidationService {

    private final JwtValidation jwtValidation;

    public jwtResponse validJwt(String jwt) {
        try {
            if (jwt == null || jwt.isEmpty()) {
                return new jwtResponse(false, "JWT está vazio");
            }

            DecodedJWT decodedJwt = JWT.decode(jwt);

            // extrai os claims
            Map<String, Claim> claims = decodedJwt.getClaims();
            String role = decodedJwt.getClaim(statics.ROLE).asString();
            String seed = decodedJwt.getClaim(statics.SEED).asString();
            String name = decodedJwt.getClaim(statics.NAME).asString();


            if (claims.size() != 3) {
                return new jwtResponse(false, " foi encontrado mais de 3 claims.");
            }

            if (!jwtValidation.isValidName(name) || !jwtValidation.isValidRole(role) || !jwtValidation.isValidateSeed(seed)) {
                String errorMessage = jwtValidation.getNameErrorMessage(name);
                return new jwtResponse(false, errorMessage);
            }

            return new jwtResponse(true, "Valid JWT");

        } catch (Exception e) {
            return new jwtResponse(false, "Invalid JWT: " + e.getMessage());
        }
    }
}