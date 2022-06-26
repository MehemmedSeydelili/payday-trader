package az.expressbank.paydaytrade.controller;

import az.expressbank.paydaytrade.model.dto.UserDto;
import az.expressbank.paydaytrade.model.request.RegisterRequest;
import az.expressbank.paydaytrade.model.response.generic.DataGeneric;
import az.expressbank.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<DataGeneric<UserDto>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        UserDto user = userService.register(registerRequest);
        return new ResponseEntity<>(DataGeneric.of(user), HttpStatus.CREATED);
    }
}
