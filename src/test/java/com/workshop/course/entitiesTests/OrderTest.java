package com.workshop.course.entitiesTests;

import com.workshop.course.entities.Order;
import com.workshop.course.entities.User;
import com.workshop.course.entities.enums.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class OrderTest {

    @Test
    public void OrderTests() {

        User roberto_santos = new User(1L, "Roberto Santos", "roberto@gmail.com", "7985475874", "123456");
        User karla_santos = new User(2L, "Karla Santos", "karla@gmail.com", "7985475874", "128456r4875dfe");

        Order order1 = new Order(5L, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, roberto_santos);
        Order order2 = new Order(15L, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, karla_santos);
        Order order3 = new Order(1254L, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, roberto_santos);

        Assertions.assertEquals(5L, order1.getId());
        Assertions.assertEquals(Instant.parse("2019-06-20T19:53:07Z"), order1.getMomento());
        Assertions.assertEquals(OrderStatus.PAID, order1.getOrderStatus());
        Assertions.assertEquals(roberto_santos, order1.getClient());

        Assertions.assertNotEquals(order1.getId(), order2.getId());

        Assertions.assertNotNull(order3.getId());
        Assertions.assertNotNull(order3.getOrderStatus());
        Assertions.assertNotNull(order3.getClient(), String.valueOf(order1.getClient()));

        Assertions.assertFalse(order2.toString().contains("Order{"));

    }
}
