package az.expressbank.paydaytrade.service;

import az.expressbank.paydaytrade.model.dto.UserDto;
import az.expressbank.paydaytrade.model.request.RegisterRequest;
import az.expressbank.paydaytrade.model.response.EmailConfirmationResponse;

import java.util.List;

public interface UserService {
    UserDto register(RegisterRequest registerRequest);

    List<UserDto> getAllUsers();

    EmailConfirmationResponse confirmation(String confirmationToken);
}
