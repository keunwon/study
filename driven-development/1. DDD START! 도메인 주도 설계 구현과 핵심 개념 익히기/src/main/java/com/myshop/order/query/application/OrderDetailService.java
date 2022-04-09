package com.myshop.order.query.application;

import com.myshop.catalog.query.product.ProductData;
import com.myshop.catalog.query.product.ProductQueryService;
import com.myshop.order.command.domain.model.Order;
import com.myshop.order.command.domain.model.OrderNo;
import com.myshop.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class OrderDetailService {
    private final OrderRepository orderRepository;
    private final ProductQueryService productQueryService;

    @Transactional
    public Optional<OrderDetail> getOrderDetail(String orderNumber) {
        Optional<Order> orderOpt = orderRepository.findById(OrderNo.of(orderNumber));
        return orderOpt.map(order -> {
            List<OrderLineDetail> orderLines = order.getOrderLines().stream()
                    .map(orderLine -> {
                        Optional<ProductData> productOpt = productQueryService.getProduct(orderLine.getProductId().getId());
                        return new OrderLineDetail(orderLine, productOpt.orElse(null));
                    }).toList();

            return new OrderDetail(order,orderLines);
        });
    }
}
