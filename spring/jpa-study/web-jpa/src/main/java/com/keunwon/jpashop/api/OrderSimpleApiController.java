package com.keunwon.jpashop.api;

import com.keunwon.jpashop.domain.Address;
import com.keunwon.jpashop.domain.Order;
import com.keunwon.jpashop.domain.OrderStatus;
import com.keunwon.jpashop.repository.OrderRepository;
import com.keunwon.jpashop.repository.OrderSearch;
import com.keunwon.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import com.keunwon.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;


    @GetMapping(path = "/api/v1/simple-orders")
    public List<Order> ordersV1() {
        return orderRepository.findAll(new OrderSearch());
    }

    @GetMapping(path = "/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        return orderRepository.findAll(new OrderSearch()).stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    @GetMapping(path = "/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        return orderRepository.findAllWithMemberDelivery().stream()
                .map(SimpleOrderDto::new)
                .collect(toList());
    }

    @GetMapping(path = "/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }


    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getName();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
        }
    }
}
