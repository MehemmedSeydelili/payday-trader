package az.expressbank.paydaytrade.mapper;

import az.expressbank.paydaytrade.domain.Authority;
import az.expressbank.paydaytrade.model.dto.AuthorityDto;
import az.expressbank.paydaytrade.model.request.CreateAuthorityRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthorityMapper {

    public AuthorityDto toAuthorityDto(Authority from){
        return new AuthorityDto(from.getId(),from.getAuthorityType());
    }

    public Authority toAuthority(CreateAuthorityRequest from){
        return Authority.builder().authorityType(from.getAuthorityType()).build();
    }

    public Set<AuthorityDto> toAuthoritySet(Set<Authority> authorities) {
        if (authorities == null) return Collections.emptySet();
        return authorities.stream().map(this::toAuthorityDto).collect(Collectors.toSet());
    }
}
