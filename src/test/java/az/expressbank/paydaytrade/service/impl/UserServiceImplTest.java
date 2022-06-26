package az.expressbank.paydaytrade.service.impl;

import az.expressbank.paydaytrade.mapper.UserMapper;
import az.expressbank.paydaytrade.repository.UserRepository;
import az.expressbank.paydaytrade.service.AuthorityService;
import az.expressbank.paydaytrade.service.EmailService;
import az.expressbank.paydaytrade.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AuthorityService authorityService;

    @Mock
    private EmailService emailService;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, userMapper, authorityService, emailService);
    }

    @Test
    void loadUserByUsername() {
    }

    @Test
    void register() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void confirmation() {
    }
}