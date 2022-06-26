package az.expressbank.paydaytrade.model.request;

import az.expressbank.paydaytrade.model.enums.AuthorityType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateAuthorityRequest {
    @NotNull
    private AuthorityType authorityType;
}
