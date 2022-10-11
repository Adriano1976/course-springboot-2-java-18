package com.workshop.course.entitiesTests;

import com.workshop.course.entities.*;
import com.workshop.course.entities.enums.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.function.BooleanSupplier;

public class ProductTest {

    @Test
    public void ProductTests() {

        User roberto_santos = new User(1L, "Roberto Santos", "roberto@gmail.com", "7985475874", "123456");
        User karla_santos = new User(2L, "Karla Santos", "karla@gmail.com", "7985475874", "128456r4875dfe");

        Category electronics = new Category(1L, "Electronics");
        Category books = new Category(2L, "Books");
        Category computers = new Category(35L, "Computers");

        Product the_lord_of_the_rings = new Product(1L, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product notbook_sunsung = new Product(2L, "Notbook Sunsung", "Lorem ipsum dolor sit amet, consectetur.", 99.5, "");
        Product pc_gamer = new Product(3L, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.00, "");

        Order order1 = new Order(5L, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, roberto_santos);
        Order order2 = new Order(15L, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, karla_santos);
        Order order3 = new Order(1254L, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, roberto_santos);

        the_lord_of_the_rings.getCategories().add(books);
        notbook_sunsung.getCategories().add(electronics);
        notbook_sunsung.getCategories().add(computers);
        pc_gamer.getCategories().add(computers);

        Payment pay1 = new Payment(2L, Instant.parse("2019-06-20T19:53:07Z"), order1);
        order1.setPayment(pay1);

        Assertions.assertEquals("Notbook Sunsung", notbook_sunsung.getName());
        Assertions.assertEquals("Lorem ipsum dolor sit amet, consectetur.", notbook_sunsung.getDescription());
        Assertions.assertEquals(99.5, notbook_sunsung.getPrice());
        Assertions.assertEquals("", notbook_sunsung.getImgUrl());

        Assertions.assertEquals(electronics.getProducts(), notbook_sunsung.getCategories());
        Assertions.assertNotEquals(notbook_sunsung.getCategories(), books.getProducts());

        Assertions.assertNotEquals(the_lord_of_the_rings.getPrice(), pc_gamer.getPrice());
        Assertions.assertNotEquals(the_lord_of_the_rings.getId(), pc_gamer.getId());
        Assertions.assertNotNull(notbook_sunsung.getId(), String.valueOf((pc_gamer.getId())));

        Assertions.assertFalse(notbook_sunsung.toString().contains("Product{"));
        Assertions.assertFalse(notbook_sunsung.getCategories().contains(books));

        Assertions.assertTrue(notbook_sunsung.getCategories().contains(books));
        Assertions.assertTrue(pc_gamer.getOrders().contains(order1));
        Assertions.assertTrue(the_lord_of_the_rings.getOrders().contains(order2));
        Assertions.assertTrue((BooleanSupplier) order1.getPayment());
    }
}
