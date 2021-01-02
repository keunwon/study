package com.keunwon.jpashop.api;

import com.keunwon.jpashop.domain.Address;
import com.keunwon.jpashop.domain.Order;
import com.keunwon.jpashop.domain.OrderItem;
import com.keunwon.jpashop.domain.OrderStatus;
import com.keunwon.jpashop.repository.OrderRepository;
import com.keunwon.jpashop.repository.OrderSearch;
import com.keunwon.jpashop.repository.order.query.OrderFlatDto;
import com.keunwon.jpashop.repository.order.query.OrderQueryDto;
import com.keunwon.jpashop.repository.order.query.OrderQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping(path = "/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            order.getOrderItems().forEach(o -> o.getItem().getName());
        }
        return all;
    }

    @GetMapping(path = "/api/v2/orders")
    public List<OrderDto> ordersV2() {
        return orderRepository.findAll(new OrderSearch()).stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping(path = "/api/v3/orders")
    public List<OrderDto> ordersV3() {
        return orderRepository.findAllWithItem().stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping(path = "/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(@RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);

        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping(path = "/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    @GetMapping(path = "/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDtoOptimization();
    }

    @GetMapping(path = "/api/v6/orders")
    public List<OrderQueryDto> ordersV6() {
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDtoFlat();

        return flats.stream()
                .collect(groupingBy(OrderFlatDto::toQueryDto,
                        mapping(OrderFlatDto::toItemQueryDto, toList())))
                .entrySet().stream()
                .map(e ->
                        new OrderQueryDto(e.getKey().getOrderId(), e.getKey().getName(),
                        e.getKey().getOrderDate(), e.getKey().getOrderStatus(), e.getKey().getAddress(), e.getValue()) )
                .collect(toList());
    }


    @Data
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;


        public OrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getName();;
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
            this.orderItems = order.getOrderItems().stream()
                    .map(OrderItemDto::new)
                    .collect(toList());
        }
    }

    @Data
    static class OrderItemDto {
        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            this.itemName = orderItem.getItem().getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();
        }
    }
}
