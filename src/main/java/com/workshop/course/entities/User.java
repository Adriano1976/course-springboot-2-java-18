package com.workshop.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
Pode forçar o Hibernate a citar um identificador no SQL gerado, anexando o nome da tabela
ou coluna em backticks no documento de mapeamento. Enquanto tradicionalmente, o Hibernate usava
backticks para escapar das palavras-chave reservadas do SQL, o JPA usa aspas duplas.

Depois que as palavras-chave reservadas forem escapadas, o Hibernate usará o estilo de cotação
correto para o SQL Dialect. Geralmente, são aspas duplas, mas o SQL Server usa colchetes e o
MySQL usa backticks.
**/

@Entity
@Table(name = "`tb_user`")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false)
    private Long id;

    @Column(name = "`name`", length = 100, nullable = false)
    private String name;
    @Column(name = "`email`", length = 100, nullable = false)
    private String email;
    @Column(name = "`phone`", length = 15, nullable = false)
    private String phone;
    @Column(name = "`password`", length = 15, nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private final List<Order> orders = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getId().equals(user.getId())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
