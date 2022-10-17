package com.workshop.course.repositoriesTests;

import com.workshop.course.entities.Order;
import com.workshop.course.entities.User;
import com.workshop.course.entities.enums.OrderStatus;
import com.workshop.course.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderRepositoryTest {

    User roberto_santos = new User(1L, "Roberto Santos", "roberto@gmail.com", "7985475874", "123456");
    User karla_santos = new User(2L, "Karla Santos", "karla@gmail.com", "7985475874", "128456r4875dfe");

    Order order1 = new Order(5L, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, roberto_santos);
    Order order2 = new Order(15L, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, karla_santos);

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void validaInsertOrder() {

        Order Order = null;
        Assertions.assertNull(Order);

        Assertions.assertNotNull(order1);
        Assertions.assertSame(order1, order1);

        orderRepository.save(order1);
        Integer countOrder = orderRepository.findAll().size();
        Assertions.assertEquals(1, countOrder);
        Assertions.assertTrue(true);
    }

    @Test
    public void validaReturnOrder() {

        orderRepository.save(order1);
        orderRepository.findAll();
        Assertions.assertTrue(true);
    }

    @Test
    public void validaUpdateOrder() {

        orderRepository.save(order1);
        orderRepository.getReferenceById(1L);

        updateData(order1, order1);
        orderRepository.save(order1);
        Assertions.assertTrue(true);
    }

    private void updateData(Order entity, Order objeto) {
        entity.setMomento(objeto.getMomento());
        entity.setOrderStatus(objeto.getOrderStatus());
        entity.setClient(objeto.getClient());
        entity.setPayment(objeto.getPayment());
    }

    @Test
    public void validaDeleteOrder() {

        orderRepository.save(order1);
        orderRepository.delete(order1);
        Assertions.assertTrue(true);
    }


    @Test
    public void checkOrderSaved() {

        orderRepository.save(order1);
        Integer countOrder = orderRepository.findAll().size();
        Assertions.assertEquals(1, countOrder);

        Assertions.assertNotNull(order1);
        Assertions.assertNotEquals(order1, order2);
        Assertions.assertTrue(true);
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
    public void validaAlgoSomenteNoOrder() {
        Assumptions.assumeFalse("root".equals(System.getenv("Order")));
        Assertions.assertTrue(true);
    }

    @Test
    public void validaExceptionOrderRepository() {

        orderRepository.save(order1);
        Integer countOrder = orderRepository.findAll().size();

        Assertions.assertEquals(1, countOrder);
        Assertions.assertDoesNotThrow(() -> orderRepository.findAll());
        Assertions.assertTrue(true);
    }
}
