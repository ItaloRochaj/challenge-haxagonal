package arqui_hexagonal_spring.adapters.inbound.dtos;

import arqui_hexagonal_spring.core.domain.Cliente;
import arqui_hexagonal_spring.core.domain.Pedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class PedidoDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotNull(message = "ID do cliente é obrigatório")
    private Long clienteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Pedido toDomain() {
        return new Pedido(this.id, this.descricao, this.data, new Cliente(this.clienteId, null, null));
    }

    public static PedidoDTO fromDomain(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setDescricao(pedido.getDescricao());
        dto.setData(pedido.getData());
        dto.setClienteId(pedido.getCliente().getId());
        return dto;
    }
}