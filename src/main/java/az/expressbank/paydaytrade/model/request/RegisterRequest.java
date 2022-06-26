package az.expressbank.paydaytrade.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterRequest {
    @NotNull @NotBlank
    @Size(min = 4, max = 20)
    private String firstName;

    @NotNull @NotBlank
    @Size(min = 4, max = 20)
    private String lastName;

    @NotNull @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    @Email @NotNull
    private String email;

    @NotNull @NotBlank
    @Size(min = 4, max = 20)
    private String password;
}
