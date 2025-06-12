package arqui_hexagonal_spring.core.services;

import arqui_hexagonal_spring.core.domain.Cliente;
import arqui_hexagonal_spring.core.ports.inbound.ClienteServicePort;
import arqui_hexagonal_spring.core.ports.outbound.ClienteRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteServicePort {

    private final ClienteRepositoryPort repository;

    public ClienteServiceImpl(ClienteRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @Override
    public Cliente buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Override
    public Cliente criar(Cliente cliente) {
        // Regra de negócio: Cliente premium tem e-mail corporativo
        if(cliente.isEmailCorporativo()) {
            cliente.setTipo("PREMIUM");
        } else {
            cliente.setTipo("REGULAR");
        }
        return repository.save(cliente);
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente existente = buscar(id);
        existente.setNome(cliente.getNome());
        existente.setEmail(cliente.getEmail());

        // Atualizar tipo baseado no novo e-mail sob a regra de negócio
        if(cliente.isEmailCorporativo()) {
            existente.setTipo("PREMIUM");
        } else {
            existente.setTipo("REGULAR");
        }

        return repository.save(existente);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}