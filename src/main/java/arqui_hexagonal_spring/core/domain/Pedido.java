package arqui_hexagonal_spring.core.domain;

import java.time.LocalDate;

public class Pedido {
    private Long id;
    private String descricao;
    private LocalDate data;
    private Cliente cliente;
    private double desconto; // Novo campo para regra de negócio

    public Pedido() {}

    public Pedido(Long id, String descricao, LocalDate data, Cliente cliente) {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public boolean isPedidoRecente() {
        return data.isAfter(LocalDate.now().minusMonths(1));
    }

    public void aplicarDesconto(double percentual) {
        this.desconto = percentual;
    }
}
