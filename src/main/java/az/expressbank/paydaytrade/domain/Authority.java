package az.expressbank.paydaytrade.domain;

import az.expressbank.paydaytrade.model.enums.AuthorityType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Authority.TABLE_NAME)
public class Authority implements Serializable {
    public static final String TABLE_NAME = "authorities";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_authority")
    @SequenceGenerator(name = "seq_authority", sequenceName = "seq_authority", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private AuthorityType authorityType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Authority authority = (Authority) o;
        return id != null && Objects.equals(id, authority.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
