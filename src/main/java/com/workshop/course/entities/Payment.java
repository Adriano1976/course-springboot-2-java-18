package com.workshop.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * Classe responsável por receber os dados da ordem de compra dos produtos e servir como
 * modelo da entidade na criação da tabela na base de dados.
 */
@Entity
@Table(name = "`tb_payment`")
public class Payment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Código do pagamento")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Long id;
    @ApiModelProperty(value = "Dia e horário do pagamento")
    private Instant moment;

    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order;

    /**
     * Construtor sem parâmetro.
     */
    public Payment() {
    }

    /**
     * Construtor com parâmetro.
     *
     * @param id     Recebe o código da compra.
     * @param moment Recebe a data e a hora do dia da compra.
     * @param order  Recebe os dados da ordem do pedido do cliente.
     */
    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment payment)) return false;

        return getId() != null ? getId().equals(payment.getId()) : payment.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
