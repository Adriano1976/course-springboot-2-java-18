package com.workshop.course.entitiesTests;

import com.workshop.course.entities.*;
import com.workshop.course.entities.enums.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

/**
 * Classe responsável por configurar, controlar e validar os testes realizados pelos métodos
 * de cadas tipo de testes e validação da entrada de dados na entidade {@link Product}.
 */
public class ProductTest {

    /**
     * Método responsável por testar e validar a entrada de dados da classe {@link Product}.
     */
    @Test
    public void ProductTests() {

        User roberto_santos = new User(null, "Roberto Santos", "roberto@gmail.com", "7985475874", "123456");
        User karla_santos = new User(null, "Karla Santos", "karla@gmail.com", "7985475874", "128456r4875dfe");

        Category electronics = new Category(null, "Electronics");
        Category books = new Category(null, "Books");
        Category computers = new Category(null, "Computers");

        Product the_lord_of_the_rings = new Product(1L, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product notbook_sunsung = new Product(2L, "Notbook Sunsung", "Lorem ipsum dolor sit amet, consectetur.", 99.5, "");
        Product pc_gamer = new Product(3L, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.00, "");

        Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, roberto_santos);
        Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, karla_santos);

        the_lord_of_the_rings.getCategories().add(books);
        notbook_sunsung.getCategories().add(electronics);
        notbook_sunsung.getCategories().add(computers);
        pc_gamer.getCategories().add(computers);

        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T19:53:07Z"), order1);
        order1.setPayment(pay1);

        Assertions.assertEquals("Notbook Sunsung", notbook_sunsung.getName());
        Assertions.assertEquals("Lorem ipsum dolor sit amet, consectetur.", notbook_sunsung.getDescription());
        Assertions.assertEquals(99.5, notbook_sunsung.getPrice());
        Assertions.assertEquals("", notbook_sunsung.getImgUrl());

        Assertions.assertEquals(electronics.getProducts(), electronics.getProducts());
        Assertions.assertNotEquals(notbook_sunsung, books);

        Assertions.assertNotEquals(the_lord_of_the_rings.getPrice(), pc_gamer.getPrice());
        Assertions.assertNotEquals(the_lord_of_the_rings.getName(), pc_gamer.getName());
        Assertions.assertNotNull(notbook_sunsung.getId(), String.valueOf((pc_gamer.getId())));

        Assertions.assertFalse(notbook_sunsung.toString().contains("Product{"));
        Assertions.assertTrue(notbook_sunsung.getCategories().contains(books));

        Assertions.assertTrue(notbook_sunsung.getCategories().contains(books));
        Assertions.assertFalse(pc_gamer.getOrders().contains(order1));
        Assertions.assertFalse(the_lord_of_the_rings.getOrders().contains(order2));
        Assertions.assertTrue(true);
    }
}
