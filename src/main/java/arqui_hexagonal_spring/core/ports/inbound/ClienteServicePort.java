package arqui_hexagonal_spring.core.ports.inbound;

import arqui_hexagonal_spring.core.domain.Cliente;
import java.util.List;

public interface ClienteServicePort {
    List<Cliente> listar();
    Cliente buscar(Long id);
    Cliente criar(Cliente cliente);
    Cliente atualizar(Long id, Cliente cliente);
    void deletar(Long id);
}
