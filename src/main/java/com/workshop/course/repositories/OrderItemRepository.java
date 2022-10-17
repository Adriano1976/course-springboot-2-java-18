package com.workshop.course.repositories;

import com.workshop.course.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ‘Interface’ responsável em informar a classe controladora de como deve ser manipulado os dados
 * conforme a extenção {@link JpaRepository}.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
