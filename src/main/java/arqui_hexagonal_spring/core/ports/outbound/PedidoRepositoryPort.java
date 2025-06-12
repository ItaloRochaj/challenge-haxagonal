package arqui_hexagonal_spring.core.ports.outbound;

import arqui_hexagonal_spring.core.domain.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {
    Pedido save(Pedido pedido);
    Optional<Pedido> findById(Long id);
    List<Pedido> findAll();
    void deleteById(Long id);
}