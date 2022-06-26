package az.expressbank.paydaytrade.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TokenProperties {

    @Value("${security.authentication.jwt.base64-secret}")
    private String secretKey;

    @Value("${security.authentication.jwt.token-validity-in-seconds}")
    private Long tokenValidityInSeconds;
}
