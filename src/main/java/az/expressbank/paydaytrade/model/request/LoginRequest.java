package az.expressbank.paydaytrade.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {
    @NotNull @NotBlank
    private String username;
    @NotNull @NotBlank
    private String password;
}
