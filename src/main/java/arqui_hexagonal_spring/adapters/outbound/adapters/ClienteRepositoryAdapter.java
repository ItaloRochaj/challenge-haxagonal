package arqui_hexagonal_spring.adapters.outbound.adapters;

import arqui_hexagonal_spring.adapters.outbound.entities.ClienteEntity;
import arqui_hexagonal_spring.adapters.outbound.repositories.ClienteJpaRepository;
import arqui_hexagonal_spring.core.domain.Cliente;
import arqui_hexagonal_spring.core.ports.outbound.ClienteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteJpaRepository jpaRepository;

    public ClienteRepositoryAdapter(ClienteJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = ClienteEntity.fromDomain(cliente);
        return jpaRepository.save(entity).toDomain();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return jpaRepository.findById(id).map(ClienteEntity::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return jpaRepository.findAll().stream()
                .map(ClienteEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}