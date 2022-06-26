package az.expressbank.paydaytrade.repository;

import az.expressbank.paydaytrade.domain.Authority;
import az.expressbank.paydaytrade.model.enums.AuthorityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthorityType(AuthorityType authorityType);
}