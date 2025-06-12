package arqui_hexagonal_spring.adapters.inbound.controllers;

import arqui_hexagonal_spring.adapters.inbound.dtos.PedidoDTO;
import arqui_hexagonal_spring.core.domain.Pedido;
import arqui_hexagonal_spring.core.ports.inbound.PedidoServicePort;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoServicePort service;

    public PedidoController(PedidoServicePort service) {
        this.service = service;
    }

    @GetMapping
    public List<PedidoDTO> listar() {
        return service.listar().stream()
                .map(PedidoDTO::fromDomain)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PedidoDTO buscar(@PathVariable Long id) {
        return PedidoDTO.fromDomain(service.buscar(id));
    }

    @PostMapping
    public PedidoDTO criar(@RequestBody @Valid PedidoDTO dto) {
        return PedidoDTO.fromDomain(service.criar(dto.toDomain()));
    }

    @PutMapping("/{id}")
    public PedidoDTO atualizar(@PathVariable Long id, @RequestBody @Valid PedidoDTO dto) {
        return PedidoDTO.fromDomain(service.atualizar(id, dto.toDomain()));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}