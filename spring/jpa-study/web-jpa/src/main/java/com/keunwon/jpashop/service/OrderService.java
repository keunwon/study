package com.keunwon.jpashop.service;

import com.keunwon.jpashop.domain.Delivery;
import com.keunwon.jpashop.domain.Member;
import com.keunwon.jpashop.domain.Order;
import com.keunwon.jpashop.domain.OrderItem;
import com.keunwon.jpashop.domain.item.Item;
import com.keunwon.jpashop.repository.ItemRepository;
import com.keunwon.jpashop.repository.MemberRepositoryOld;
import com.keunwon.jpashop.repository.OrderRepository;
import com.keunwon.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepositoryOld memberRepositoryOld;
    private final ItemRepository itemRepository;


    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepositoryOld.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }


    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
