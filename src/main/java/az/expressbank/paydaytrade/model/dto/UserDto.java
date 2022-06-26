package az.expressbank.paydaytrade.model.dto;

import az.expressbank.paydaytrade.model.dto.AuthorityDto;

import java.util.Set;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        Set<AuthorityDto> authorities) {
}
