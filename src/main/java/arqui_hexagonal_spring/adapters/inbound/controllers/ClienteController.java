package arqui_hexagonal_spring.adapters.inbound.controllers;

import arqui_hexagonal_spring.adapters.inbound.dtos.ClienteDTO;
import arqui_hexagonal_spring.core.domain.Cliente;
import arqui_hexagonal_spring.core.ports.inbound.ClienteServicePort;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServicePort service;

    public ClienteController(ClienteServicePort service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteDTO> listar() {
        return service.listar().stream()
                .map(ClienteDTO::fromDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClienteDTO buscar(@PathVariable Long id) {
        return ClienteDTO.fromDomain(service.buscar(id));
    }

    @PostMapping
    public ClienteDTO criar(@RequestBody @Valid ClienteDTO dto) {
        return ClienteDTO.fromDomain(service.criar(dto.toDomain()));
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable Long id, @RequestBody @Valid ClienteDTO dto) {
        return ClienteDTO.fromDomain(service.atualizar(id, dto.toDomain()));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}