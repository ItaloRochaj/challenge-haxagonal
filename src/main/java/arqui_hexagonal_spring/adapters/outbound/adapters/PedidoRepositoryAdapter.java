package arqui_hexagonal_spring.adapters.outbound.adapters;

import arqui_hexagonal_spring.adapters.outbound.entities.PedidoEntity;
import arqui_hexagonal_spring.adapters.outbound.repositories.PedidoJpaRepository;
import arqui_hexagonal_spring.core.domain.Pedido;
import arqui_hexagonal_spring.core.ports.outbound.PedidoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository jpaRepository;

    public PedidoRepositoryAdapter(PedidoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Pedido save(Pedido pedido) {
        PedidoEntity entity = PedidoEntity.fromDomain(pedido);
        return jpaRepository.save(entity).toDomain();
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return jpaRepository.findById(id).map(PedidoEntity::toDomain);
    }

    @Override
    public List<Pedido> findAll() {
        return jpaRepository.findAll().stream()
                .map(PedidoEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}