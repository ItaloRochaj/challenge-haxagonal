package arqui_hexagonal_spring.adapters.outbound.repositories;

import arqui_hexagonal_spring.adapters.outbound.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {}