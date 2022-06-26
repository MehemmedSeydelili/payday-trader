package az.expressbank.paydaytrade.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class JwtAuthenticationToken {
    private String token;
    private Long tokenValidityInMilliseconds;
}
