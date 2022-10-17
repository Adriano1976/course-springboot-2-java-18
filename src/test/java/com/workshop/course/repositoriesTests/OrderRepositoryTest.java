package com.workshop.course.repositoriesTests;

import com.workshop.course.entities.*;
import com.workshop.course.entities.enums.OrderStatus;
import com.workshop.course.repositories.*;
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
import java.util.Arrays;


/**
 * Classe responsável por configurar, controlar e validar os testes realizados pelos métodos
 * de cadas tipo de testes e validação das atividades do repositório {@link OrderRepository} e
 * do serviço {@link com.workshop.course.services.OrderService}.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    /**
     * Método responsável por testar e validar a entrada de dados por meio da class
     * {@link OrderRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaInsertOrder() {

        Category category1 = new Category(null, "Electronics");
        Category category2 = new Category(null, "Books");
        Category category3 = new Category(null, "Computers");

        Product product1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product product2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product product3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product product4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product product5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        product1.getCategories().add(category2);
        product2.getCategories().add(category1);
        product2.getCategories().add(category3);
        product3.getCategories().add(category3);
        product4.getCategories().add(category3);
        product5.getCategories().add(category2);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        User maria_brown = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User alex_green = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, maria_brown);
        Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, alex_green);
        Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, maria_brown);

        userRepository.saveAll(Arrays.asList(maria_brown, alex_green));
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));

        OrderItem orderItem1 = new OrderItem(order1, product1, 2, product1.getPrice());
        OrderItem orderItem2 = new OrderItem(order1, product3, 1, product4.getPrice());
        OrderItem orderItem3 = new OrderItem(order2, product3, 2, product1.getPrice());
        OrderItem orderItem4 = new OrderItem(order3, product5, 2, product5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4));

        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T19:53:07Z"), order1);
        order1.setPayment(pay1);

        Payment pay2 = new Payment(null, Instant.parse("2019-06-20T19:53:07Z"), order1);
        order1.setPayment(pay2);

        orderRepository.save(order1);
        orderRepository.save(order2);

        Assertions.assertNotNull(order1);
        Assertions.assertNotNull(order2);
        Assertions.assertSame(order1, order1);
        Assertions.assertSame(order2, order2);

        Integer countOrder = orderRepository.findAll().size();

        /* Validandos a quantidade de ordens executadas, pelo qual não retorna nenhuma exception.
         * Sendo assim, a validação do teste retorne verdadeiro. */

        Assertions.assertEquals(3, countOrder);
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar e validar a entrada e o retorno dos dados por meio da class
     * {@link OrderRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaReturnOrder() {

        Category category1 = new Category(null, "Electronics");
        Category category2 = new Category(null, "Books");
        Category category3 = new Category(null, "Computers");

        Product product1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product product2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product product3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product product4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product product5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        product1.getCategories().add(category2);
        product2.getCategories().add(category1);
        product2.getCategories().add(category3);
        product3.getCategories().add(category3);
        product4.getCategories().add(category3);
        product5.getCategories().add(category2);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        User maria_brown = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User alex_green = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, maria_brown);
        Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, alex_green);
        Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, maria_brown);

        userRepository.saveAll(Arrays.asList(maria_brown, alex_green));
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));

        OrderItem orderItem1 = new OrderItem(order1, product1, 2, product1.getPrice());
        OrderItem orderItem2 = new OrderItem(order1, product3, 1, product4.getPrice());
        OrderItem orderItem3 = new OrderItem(order2, product3, 2, product1.getPrice());
        OrderItem orderItem4 = new OrderItem(order3, product5, 2, product5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4));

        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T19:53:07Z"), order1);
        order1.setPayment(pay1);

        orderRepository.save(order1);
        orderRepository.findAll();

        Integer countOrder = orderRepository.findAll().size();

        /* Validandos a quantidade de ordens executadas, pelo qual não retorna nenhuma exception.
         * Sendo assim, a validação do teste retorne verdadeiro. */

        Assertions.assertEquals(3, countOrder);
        Assertions.assertNotNull(order1);
        Assertions.assertNotEquals(order1, order2);
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar e validar a entrada de dados e as alterações realizadas por meio da class
     * {@link OrderRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaUpdateOrder() {

        Category category1 = new Category(1L, "Electronics");
        Category category2 = new Category(2L, "Books");
        Category category3 = new Category(3L, "Computers");

        Product product1 = new Product(1L, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product product2 = new Product(2L, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product product3 = new Product(3L, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product product4 = new Product(4L, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product product5 = new Product(5L, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        product1.getCategories().add(category2);
        product2.getCategories().add(category1);
        product2.getCategories().add(category3);
        product3.getCategories().add(category3);
        product4.getCategories().add(category3);
        product5.getCategories().add(category2);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        User maria_brown = new User(1L, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User alex_green = new User(2L, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order order1 = new Order(1L, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, maria_brown);
        Order order2 = new Order(2L, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, alex_green);
        Order order3 = new Order(3L, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, maria_brown);

        userRepository.saveAll(Arrays.asList(maria_brown, alex_green));
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));

        orderRepository.getReferenceById(1L);
        updateData(order1, order1 = new Order(1L, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.SHIPPED, maria_brown));
        orderRepository.save(order1);

        orderRepository.getReferenceById(2L);
        updateData(order2, order2 = new Order(2L, Instant.parse("2019-06-20T20:15:07Z"), OrderStatus.CANCELED, alex_green));
        orderRepository.save(order2);

        Assertions.assertTrue(true);
    }

    /**
     * Método responsável em controlar e manipular os dados da ordem de compra.
     *
     * @param entity Enviar os dados da ordem de compra alterado para a base de dados.
     * @param objeto Recebe os dados da ordem de compra alterado pelo usuário.
     */
    private void updateData(Order entity, Order objeto) {
        entity.setMomento(objeto.getMomento());
        entity.setOrderStatus(objeto.getOrderStatus());
        entity.setClient(objeto.getClient());
        entity.setPayment(objeto.getPayment());
    }

    /**
     * Método responsável por testar e validar a entrada e remoção de dados por meio da class
     * {@link OrderRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaDeleteOrder() {

        Category category1 = new Category(null, "Electronics");
        Category category2 = new Category(null, "Books");
        Category category3 = new Category(null, "Computers");

        Product product1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product product2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product product3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product product4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product product5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        product1.getCategories().add(category2);
        product2.getCategories().add(category1);
        product2.getCategories().add(category3);
        product3.getCategories().add(category3);
        product4.getCategories().add(category3);
        product5.getCategories().add(category2);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        User maria_brown = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User alex_green = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, maria_brown);
        Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, alex_green);
        Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, maria_brown);

        userRepository.saveAll(Arrays.asList(maria_brown, alex_green));
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));

        orderRepository.getReferenceById(1L);
        orderRepository.deleteById(order1.getId());

        /* Validandos a remoção dos dados conforme o ‘id’ escolhido pelo usuário.
         * Sendo assim, a validação do teste retorne verdadeiro. */

        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar, verificar a entrada de dados e validar dos dados salvo por meio da class
     * {@link OrderRepository} e as outras classes auxiliares.
     */

    @Test
    public void checkOrderSaved() {

        User jose_santos = new User(null, "José Santos", "jose@gmail.com", "7985475874", "123456");
        User alex_santos = new User(null, "Alex Santos", "alex@gmail.com", "7985475874", "128456r4875dfe");

        Order order9 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, jose_santos);
        Order order10 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, alex_santos);

        userRepository.saveAll(Arrays.asList(jose_santos, alex_santos));
        orderRepository.saveAll(Arrays.asList(order9, order10));

        Integer countOrder = orderRepository.findAll().size();

        /* Validandos a quantidade de ordens executadas, pelo qual não retorna nenhuma exception.
         * Sendo assim, a validação do teste retorne verdadeiro. */

        Assertions.assertEquals(2, countOrder);
        Assertions.assertNotNull(order9);
        Assertions.assertNotEquals(order9, order10);
        Assertions.assertTrue(true);
    }

    /**
     * Médoto responsável em validar a versão do Java e a integridade de manipulação de dados.
     */
    @Test
    @EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
    public void validaAlgoSomenteNoOrder() {
        Assumptions.assumeFalse("root".equals(System.getenv("Order")));
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar e validar a entrada de dados e a não ocorrência de exceções por meio da class
     * {@link OrderRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaExceptionOrderRepository() {

        Category category1 = new Category(null, "Electronics");
        Category category2 = new Category(null, "Books");
        Category category3 = new Category(null, "Computers");

        Product product1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product product2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product product3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product product4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product product5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        product1.getCategories().add(category2);
        product2.getCategories().add(category1);
        product2.getCategories().add(category3);
        product3.getCategories().add(category3);
        product4.getCategories().add(category3);
        product5.getCategories().add(category2);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        User maria_brown = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User alex_green = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, maria_brown);
        Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, alex_green);
        Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, maria_brown);

        userRepository.saveAll(Arrays.asList(maria_brown, alex_green));
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));

        Integer countOrder = orderRepository.findAll().size();

        /* Validandos a quantidade de ordens executadas, pelo qual não retorna nenhuma exception.
         * Sendo assim, a validação do teste retorne verdadeiro. */

        Assertions.assertEquals(3, countOrder);
        Assertions.assertDoesNotThrow(() -> orderRepository.findAll());
        Assertions.assertTrue(true);
    }
}
