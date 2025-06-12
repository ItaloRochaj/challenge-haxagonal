package arqui_hexagonal_spring.adapters.outbound.repositories;


import arqui_hexagonal_spring.adapters.outbound.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, Long> {}