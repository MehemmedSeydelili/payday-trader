package az.expressbank.paydaytrade.service;

import az.expressbank.paydaytrade.domain.Authority;
import az.expressbank.paydaytrade.model.dto.AuthorityDto;
import az.expressbank.paydaytrade.model.enums.AuthorityType;
import az.expressbank.paydaytrade.model.request.CreateAuthorityRequest;

public interface AuthorityService {
    Authority findByAuthorityType(AuthorityType authorityType);
    AuthorityDto createAuthority(CreateAuthorityRequest createAuthorityRequest);
}
