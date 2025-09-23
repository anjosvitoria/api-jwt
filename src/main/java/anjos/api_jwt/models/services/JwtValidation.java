package anjos.api_jwt.models.services;

import org.springframework.stereotype.Component;

import static anjos.api_jwt.models.enums.statics.VALID_ROLES;

@Component
public class JwtValidation {

    // ---------- NAME ----------
    public boolean isValidName(String name) {
        return getNameErrorMessage(name) == null;
    }

    public String getNameErrorMessage(String name) {
        if (name == null) {
            return "Name não pode ser nulo";
        }
        if (name.chars().anyMatch(Character::isDigit)) {
            return "Name não pode conter dígitos";
        }
        if (name.length() > 256) {
            return "Name não pode ter mais de 256 caracteres";
        }
        return null; // válido
    }

    // ---------- ROLE ----------
    public boolean isValidRole(String role) {
        return getRoleErrorMessage(role) == null;
    }

    public String getRoleErrorMessage(String role) {
        if (role == null) {
            return "Role não pode ser nulo";
        }
        if (!VALID_ROLES.contains(role)) {
            return "Invalid role: " + role;
        }
        return null;
    }

    // ---------- SEED ----------
    public boolean isValidateSeed(String seed) {
        return getSeedErrorMessage(seed) == null;
    }

    public String getSeedErrorMessage(String seed) {
        if (seed == null) {
            return "Seed não pode ser nulo";
        }
        try {
            int seedInt = Integer.parseInt(seed);
            if (!isPrime(seedInt)) {
                return "Seed deve ser um número primo";
            }
            return null;
        } catch (NumberFormatException e) {
            return "Seed deve ser um número válido";
        }
    }

    // ---------- AUX ----------
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
