package com.workshop.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.workshop.course.entities.enums.OrderStatus;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe responsável por receber os dados da ordem de compra dos produtos e servir como
 * modelo da entidade na criação da tabela na base de dados.
 */
@Entity
@Table(name = "`tb_order`")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @OneToMany(mappedBy = "id.order")
    private final Set<OrderItem> items = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd 'T' HH:mm:ss 'Z'", timezone = "GMT")
    private Instant momento;
    private Integer orderStatus;
    @ManyToOne
    @JoinColumn(name = "`client_id`")
    private User client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    /**
     * Construtor sem parâmetro.
     */
    public Order() {
    }

    /**
     * Construtor com parâmetro.
     *
     * @param id          Recebe o código de identificação da ordem de pagamento.
     * @param momento     Recebe a data e hora do exato momento da ordem do pagamento.
     * @param orderStatus Recebe o status da ordem de pagamento.
     * @param client      Recebe os dados do cliente que irá pagar o produto.
     */
    public Order(Long id, Instant momento, OrderStatus orderStatus, User client) {
        this.id = id;
        this.momento = momento;
        this.setOrderStatus(orderStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMomento() {
        return momento;
    }

    public void setMomento(Instant momento) {
        this.momento = momento;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (OrderItem orderItem : items) {
            sum += orderItem.getSubTotal();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;

        return getId().equals(order.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
