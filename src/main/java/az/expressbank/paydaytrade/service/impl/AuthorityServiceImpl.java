package az.expressbank.paydaytrade.service.impl;

import az.expressbank.paydaytrade.domain.Authority;
import az.expressbank.paydaytrade.error.ResourceNotFoundException;
import az.expressbank.paydaytrade.mapper.AuthorityMapper;
import az.expressbank.paydaytrade.model.dto.AuthorityDto;
import az.expressbank.paydaytrade.model.enums.AuthorityType;
import az.expressbank.paydaytrade.model.request.CreateAuthorityRequest;
import az.expressbank.paydaytrade.repository.AuthorityRepository;
import az.expressbank.paydaytrade.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    @Override
    public Authority findByAuthorityType(AuthorityType authorityType) {
        return authorityRepository.findByAuthorityType(authorityType)
                .orElseThrow(() -> new ResourceNotFoundException(format("Authority was not found with authorityType: %s", authorityType)));
    }

    @Override
    public AuthorityDto createAuthority(CreateAuthorityRequest createAuthorityRequest) {
        Authority authority = authorityRepository.save(authorityMapper.toAuthority(createAuthorityRequest));
        return authorityMapper.toAuthorityDto(authority);
    }
}
