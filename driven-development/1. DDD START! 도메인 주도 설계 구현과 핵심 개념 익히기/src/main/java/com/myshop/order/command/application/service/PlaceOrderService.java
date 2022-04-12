package com.myshop.order.command.application.service;

import com.myshop.catalog.command.domain.product.model.Product;
import com.myshop.catalog.command.domain.product.model.ProductId;
import com.myshop.catalog.command.domain.product.ProductRepository;
import com.myshop.common.ValidationError;
import com.myshop.common.ValidationErrorException;
import com.myshop.order.command.application.dto.OrderProduct;
import com.myshop.order.command.application.dto.OrderRequest;
import com.myshop.order.command.application.dto.OrderRequestValidator;
import com.myshop.order.command.application.exception.NoOrderProductException;
import com.myshop.order.command.domain.model.OrderNo;
import com.myshop.order.command.domain.service.OrdererService;
import com.myshop.order.command.domain.model.Order;
import com.myshop.order.command.domain.model.OrderLine;
import com.myshop.order.command.domain.model.OrderState;
import com.myshop.order.command.domain.model.Orderer;
import com.myshop.order.command.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlaceOrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrdererService ordererService;

    @Transactional
    public OrderNo placeOrder(OrderRequest orderRequest) {
        List<ValidationError> errors = validateOrderRequest(orderRequest);
        if (!errors.isEmpty()) { throw new ValidationErrorException(errors); }

        List<OrderLine> orderLines = new ArrayList<>();
        for (OrderProduct op : orderRequest.getOrderProducts()) {
            Product product = productRepository.findById(new ProductId(op.getProductId()))
                    .orElseThrow(() -> new NoOrderProductException(op.getProductId()));
            orderLines.add(new OrderLine(product.getId(), product.getPrice(), op.getQuantity()));
        }
        OrderNo orderNo = orderRepository.nextOrderNo();
        Orderer orderer = ordererService.createOrderer(orderRequest.getOrdererMemberId());

        Order order = new Order(orderNo, orderer, orderLines, orderRequest.getShippingInfo(), OrderState.PAYMENT_WAITING);
        orderRepository.save(order);
        return orderNo;
    }

    private List<ValidationError> validateOrderRequest(OrderRequest orderRequest) {
        return new OrderRequestValidator().validate(orderRequest);
    }
}
