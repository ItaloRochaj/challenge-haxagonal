package arqui_hexagonal_spring.adapters.outbound.entities;

import arqui_hexagonal_spring.core.domain.Pedido;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    public PedidoEntity() {}

    public PedidoEntity(Long id, String descricao, LocalDate data, ClienteEntity cliente) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.cliente = cliente;
    }

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

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public static PedidoEntity fromDomain(Pedido pedido) {
        return new PedidoEntity(
                pedido.getId(),
                pedido.getDescricao(),
                pedido.getData(),
                ClienteEntity.fromDomain(pedido.getCliente())
        );
    }

    public Pedido toDomain() {
        return new Pedido(
                this.id,
                this.descricao,
                this.data,
                this.cliente != null ? this.cliente.toDomain() : null
        );
    }
}