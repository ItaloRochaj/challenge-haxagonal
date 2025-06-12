package arqui_hexagonal_spring.core.ports.inbound;

import arqui_hexagonal_spring.core.domain.Pedido;
import java.util.List;

public interface PedidoServicePort {
    List<Pedido> listar();
    Pedido buscar(Long id);
    Pedido criar(Pedido pedido);
    Pedido atualizar(Long id, Pedido pedido);
    void deletar(Long id);
}
