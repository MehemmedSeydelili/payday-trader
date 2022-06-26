package az.expressbank.paydaytrade.controller;

import az.expressbank.paydaytrade.model.request.LoginRequest;
import az.expressbank.paydaytrade.model.response.EmailConfirmationResponse;
import az.expressbank.paydaytrade.model.response.JwtAuthenticationToken;
import az.expressbank.paydaytrade.model.response.generic.DataGeneric;
import az.expressbank.paydaytrade.security.TokenProvider;
import az.expressbank.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authenticate")
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping
    public ResponseEntity<DataGeneric<JwtAuthenticationToken>> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        var authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var token = tokenProvider.createToken(authentication);
        var jwtAuthenticationToken = JwtAuthenticationToken.of(token, tokenProvider.getTokenValidityInMilliseconds());
        return ResponseEntity.ok(DataGeneric.of(jwtAuthenticationToken));
    }

    @GetMapping("/confirmation")
    public ResponseEntity<DataGeneric<EmailConfirmationResponse>> confirmation(
            @RequestParam(value = "confirmationToken") String confirmationToken) {
        return ResponseEntity.ok(DataGeneric.of(userService.confirmation(confirmationToken)));
    }
}
