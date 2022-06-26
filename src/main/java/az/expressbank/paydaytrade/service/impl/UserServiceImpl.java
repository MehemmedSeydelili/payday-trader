package az.expressbank.paydaytrade.service.impl;

import az.expressbank.paydaytrade.domain.Authority;
import az.expressbank.paydaytrade.domain.User;
import az.expressbank.paydaytrade.error.ResourceConflictException;
import az.expressbank.paydaytrade.mapper.UserMapper;
import az.expressbank.paydaytrade.model.dto.UserDto;
import az.expressbank.paydaytrade.model.enums.AuthorityType;
import az.expressbank.paydaytrade.model.request.RegisterRequest;
import az.expressbank.paydaytrade.model.response.EmailConfirmationResponse;
import az.expressbank.paydaytrade.repository.UserRepository;
import az.expressbank.paydaytrade.security.UserPrincipal;
import az.expressbank.paydaytrade.service.AuthorityService;
import az.expressbank.paydaytrade.service.EmailService;
import az.expressbank.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityService authorityService;
    private final EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException(
                        format("User was not found with username: %s", username)));
    }

    @Override
    public UserDto register(RegisterRequest registerRequest) {
        if (doesExistByUsername(registerRequest.getUsername()))
            throw new ResourceConflictException(format("User already exists with username: %s", registerRequest.getUsername()));

        if (doesExistByEmail(registerRequest.getEmail()))
            throw new ResourceConflictException(format("User already exists with email: %s", registerRequest.getEmail()));

        Authority authority = authorityService.findByAuthorityType(AuthorityType.ROLE_USER);
        User user = userMapper.toUser(registerRequest, authority);
        emailService.sendMail(user.getEmail(),
                "Confirmation",
                "http://localhost:8080/api/v1/authenticate/confirmation?confirmationToken=" + user.getConfirmationToken());
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }

    @Override
    public EmailConfirmationResponse confirmation(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken)
                .map(user -> {
                    user.setActivated(true);
                    userRepository.save(user);
                    return EmailConfirmationResponse.of(true, "Email successfully confirmed");
                })
                .orElse(EmailConfirmationResponse.of(false, "Email was not confirmed"));
    }

    private boolean doesExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean doesExistByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
