package com.workshop.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe responsável por receber os dados da categoria dos produtos e servir como
 * modelo da entidade na criação da tabela na base de dados.
 */
@Entity
@Table(name = "`tb_category`")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonIgnore  // Ele não retorna no JSON, mas no código ainda tem acesso a ele e pode trabalhar com ele.
    @ManyToMany(mappedBy = "categories")
    private final Set<Product> products = new HashSet<>();
    @ApiModelProperty(value = "Código da categoria do produto")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false)
    private Long id;
    @ApiModelProperty(value = "Nome da categoria do produto")
    @Column(name = "`name`", length = 100, nullable = false)
    private String name;

    /**
     * Construtor sem parâmetro.
     */
    public Category() {
    }

    /**
     * Cosntrutor com parâmetro.
     *
     * @param id   Recebe o código de identificação da categoria do produto.
     * @param name Recebe o nome da categoria do produto.
     */
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;

        return getId() != null ? getId().equals(category.getId()) : category.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
