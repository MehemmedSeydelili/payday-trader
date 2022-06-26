package az.expressbank.paydaytrade.mapper;

import az.expressbank.paydaytrade.domain.Authority;
import az.expressbank.paydaytrade.domain.User;
import az.expressbank.paydaytrade.model.dto.UserDto;
import az.expressbank.paydaytrade.model.enums.AuthorityType;
import az.expressbank.paydaytrade.model.request.RegisterRequest;
import az.expressbank.paydaytrade.service.AuthorityService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public record UserMapper(
        PasswordEncoder passwordEncoder,
        AuthorityMapper authorityMapper) {

    public User toUser(RegisterRequest from, Authority authority) {
        return User.builder()
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .email(from.getEmail().toLowerCase())
                .username(from.getUsername().toLowerCase())
                .activated(false)
                .password(passwordEncoder.encode(from.getPassword()))
                .authorities(Set.of(authority))
                .confirmationToken(UUID.randomUUID().toString())
                .build();
    }

    public UserDto toUserDto(User from) {
        return new UserDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getUsername(),
                from.getEmail(),
                authorityMapper.toAuthoritySet(from.getAuthorities())
        );
    }

    public List<UserDto> toUserDtoList(List<User> all) {
        if (all == null) return Collections.emptyList();
        return all.stream().map(this::toUserDto).toList();
    }
}
