package anjos.api_jwt.models.enums;

import java.util.Arrays;
import java.util.List;

public class statics {
    public static final String SECRET = "secretKeyForJWT";
    public static final List<String> VALID_ROLES = Arrays.asList("Admin", "Member", "External");

}
