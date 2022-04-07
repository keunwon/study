package com.myshop.order.infrastructure;

import com.myshop.order.domain.Order;
import com.myshop.order.domain.OrderNo;
import com.myshop.order.domain.OrderRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaOrderRepository implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order findById(OrderNo no) {
        return entityManager.find(Order.class, no);
    }

    @Override
    public void save(Order order) {
        entityManager.persist(order);
    }

    @Override
    public List<Order> findByOrdererId(String ordererId, int startRow, int size) {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o" +
                        "where o.orderer.memberId.id = :ordererId" +
                        "order by o.number.number desc",
                Order.class);

        query.setParameter("orderId", ordererId);
        query.setFirstResult(startRow);
        query.setMaxResults(size);

        return query.getResultList();
    }

    @Override
    public void delete(Order order) {
        entityManager.remove(order);
    }
}
