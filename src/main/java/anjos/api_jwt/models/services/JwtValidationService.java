package anjos.api_jwt.models.services;

import anjos.api_jwt.dto.jwtRequest;
import anjos.api_jwt.dto.jwtResponse;
import anjos.api_jwt.models.enums.statics;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
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
                return new jwtResponse(false, "JWT token is empty");
            }

            // cria algoritmo com sua secret
            Algorithm algorithm = Algorithm.HMAC256(statics.SECRET);

            // cria o verificador que valida assinatura e expiração
            JWTVerifier verifier = JWT.require(algorithm).build();

            // valida o token e já retorna o DecodedJWT
            DecodedJWT decodedJwt = verifier.verify(jwt);

            // extrai os claims
            Map<String, Claim> claims = decodedJwt.getClaims();
            String name = decodedJwt.getClaim("name").asString();
            String role = decodedJwt.getClaim("role").asString();
            String seed = decodedJwt.getClaim("seed").asString();

            if (claims.size() != 3) {
                return new jwtResponse(false, "Invalid number of claims");
            }

            if (!jwtValidation.isValidName(name) || !jwtValidation.isValidRole(role) || !jwtValidation.isValidateSeed(seed)) {
                return new jwtResponse(false, "Invalid claim values");
            }

            return new jwtResponse(true, "Valid JWT");

        } catch (com.auth0.jwt.exceptions.TokenExpiredException e) {
            return new jwtResponse(false, "JWT token has expired");
        } catch (com.auth0.jwt.exceptions.SignatureVerificationException e) {
            return new jwtResponse(false, "Invalid JWT signature");
        } catch (Exception e) {
            return new jwtResponse(false, "Invalid JWT: " + e.getMessage());
        }
    }

    public String generateToken(jwtRequest request) {
    }
}