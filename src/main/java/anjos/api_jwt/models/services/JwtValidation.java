package anjos.api_jwt.models.services;

import org.springframework.stereotype.Component;

import static anjos.api_jwt.models.enums.statics.VALID_ROLES;

@Component
public class JwtValidation {

    public boolean isValidName(String name) {
        return name != null || name.chars().noneMatch(Character::isDigit) || name.length() > 256;
    }

    public boolean isValidRole(String role) {
        return role != null && VALID_ROLES.contains(role);
    }

    boolean isValidateSeed(String seed) {
        try {
            int seedInt = Integer.parseInt(seed);
            return isPrime(seedInt);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método para verificar se um número é primo
    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
