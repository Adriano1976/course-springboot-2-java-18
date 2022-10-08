package com.workshop.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`tb_product`")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @ManyToMany
    @JoinTable(name = "`tb_product_category`",
            joinColumns = @JoinColumn(name = "`product_id`"),
            inverseJoinColumns = @JoinColumn(name = "`category_id`"))
    private final Set<Category> categories = new HashSet<>();
    @OneToMany(mappedBy = "id.product")
    private final Set<OrderItem> items = new HashSet<>();
    @ApiModelProperty(value = "Código do produto")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false)
    private Long id;
    @ApiModelProperty(value = "Nome do produto")
    @Column(name = "`name`", length = 100, nullable = false)
    private String name;
    @ApiModelProperty(value = "Descrição do produto")
    @Column(name = "`description`", length = 200, nullable = false)
    private String description;
    @ApiModelProperty(value = "Preço do produto")
    @Column(name = "`price`", length = 10, nullable = false)
    private Double price;
    @ApiModelProperty(value = "Url da imagem do produto")
    @Column(name = "`imgUrl`", length = 200, nullable = false)
    private String imgUrl;

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @JsonIgnore  // Ele não retorna no JSON, mas no código ainda tem acesso a ele e pode trabalhar com ele.
    public Set<Order> getOrders() {
        Set<Order> set = new HashSet<>();
        for (OrderItem orderItem : items) {
            set.add(orderItem.getOrder());
        }
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;

        return getId() != null ? getId().equals(product.getId()) : product.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
