package arqui_hexagonal_spring.core.services;

import arqui_hexagonal_spring.core.domain.Pedido;
import arqui_hexagonal_spring.core.ports.inbound.PedidoServicePort;
import arqui_hexagonal_spring.core.ports.outbound.PedidoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoServicePort {

    private final PedidoRepositoryPort repository;

    public PedidoServiceImpl(PedidoRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Pedido> listar() {
        return repository.findAll();
    }

    @Override
    public Pedido buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    @Override
    public Pedido criar(Pedido pedido) {
        // Regra de negócio: Clientes premiu ganham 10% de desconto
        if ("PREMIUM".equals(pedido.getCliente().getTipo())) {
            pedido.aplicarDesconto(0.10);
        }

        // Regra de negócio: Pedidos antigos não são permitidos
        if (!pedido.isPedidoRecente()) {
            throw new RuntimeException("Não é possível criar pedidos com mais de 1 mês");
        }

        return repository.save(pedido);
    }

    @Override
    public Pedido atualizar(Long id, Pedido pedido) {
        Pedido existente = buscar(id);
        existente.setDescricao(pedido.getDescricao());
        existente.setData(pedido.getData());
        existente.setCliente(pedido.getCliente());
        existente.setDesconto(pedido.getDesconto());

        // Atualizar desconto se cliente mudou
        if ("PREMIUM".equals(pedido.getCliente().getTipo())) {
            existente.aplicarDesconto(0.10);
        } else {
            existente.setDesconto(0);
        }

        return repository.save(existente);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}