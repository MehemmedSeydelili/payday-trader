package az.expressbank.paydaytrade.model.dto;

import az.expressbank.paydaytrade.model.enums.AuthorityType;

public record AuthorityDto(
        Long id,
        AuthorityType authorityType) {
}
