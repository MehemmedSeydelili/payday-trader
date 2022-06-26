package az.expressbank.paydaytrade;

import az.expressbank.paydaytrade.domain.Authority;
import az.expressbank.paydaytrade.model.enums.AuthorityType;
import az.expressbank.paydaytrade.repository.AuthorityRepository;
import az.expressbank.paydaytrade.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class PayTraderApplication implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(PayTraderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        authorityRepository.deleteAll();
        List<Authority> authorities = List.of(
                Authority.builder().authorityType(AuthorityType.ROLE_USER).build(),
                Authority.builder().authorityType(AuthorityType.ROLE_ADMIN).build());
        authorityRepository.saveAll(authorities);
    }
}
