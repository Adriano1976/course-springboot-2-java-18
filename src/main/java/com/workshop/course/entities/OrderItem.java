package com.workshop.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workshop.course.entities.pk.OrderItemPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Classe responsável por receber os dados da ordem dos itens da compra dos produtos e servir como
 * modelo da entidade na criação da tabela na base de dados.
 */
@Entity
@Table(name = "`tb_order_item`")
public class OrderItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private final OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    /**
     * Construtor sem parâmetro.
     */
    public OrderItem() {
    }

    /**
     * Contrutor com parâmetro.
     *
     * @param order    Recebe os dados da ordem do pedido.
     * @param product  Recebe os dados do produto referente a ordem do pedido.
     * @param quantity Recebe a quantidade dos produtos referente a ordem de pedido.
     * @param price    Recebe o valor do produto.
     */
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore  // Ele não retorna no JSON, mas no código ainda tem acesso a ele e pode trabalhar com ele.
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal() {
        return price * quantity;
    }

    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem orderItem)) return false;

        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
