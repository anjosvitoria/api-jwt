package anjos.api_jwt.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class jwtResponse {

    private boolean validPassword;

    private String message;
}
