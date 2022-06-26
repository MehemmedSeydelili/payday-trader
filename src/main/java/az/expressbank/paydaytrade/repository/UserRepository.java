package az.expressbank.paydaytrade.repository;

import az.expressbank.paydaytrade.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<User> findByConfirmationToken(String confirmationToken);
}