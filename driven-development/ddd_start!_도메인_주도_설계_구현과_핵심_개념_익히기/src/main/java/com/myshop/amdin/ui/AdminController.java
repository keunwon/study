package com.myshop.amdin.ui;

import com.myshop.common.ui.Pagination;
import com.myshop.order.command.application.dto.StartShippingRequest;
import com.myshop.order.command.application.service.StartShippingService;
import com.myshop.order.query.application.ListRequest;
import com.myshop.order.query.application.OrderDetail;
import com.myshop.order.query.application.OrderDetailService;
import com.myshop.order.query.application.OrderViewListService;
import com.myshop.order.query.dto.OrderSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final OrderViewListService orderViewListService;
    private final OrderDetailService orderDetailService;
    private final StartShippingService startShippingService;

    @GetMapping(value = "/admin/orders")
    public String orders(@RequestParam(name = "p", defaultValue = "1") int page,
                         ModelMap modelMap) {
        ListRequest listRequest = new ListRequest(page - 1, 20);
        Page<OrderSummary> orderPage = orderViewListService.getList(listRequest);

        modelMap.addAttribute(orderPage);
        modelMap.addAttribute("pagination",
                new Pagination(orderPage.getNumber() + 1, orderPage.getTotalPages(), 5));

        return "admin/adminOrders";
    }

    @GetMapping(value = "/admin/orders/{orderNo}")
    public String orderDetail(@PathVariable("orderNo") String orderNo, ModelMap modelMap) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetail(orderNo);

        if (orderDetail.isPresent()) {
            modelMap.addAttribute("order", orderDetail.get());
            return "admin/adminOrderDetail";
        }
        return "admin/noOrder";
    }

    @PostMapping(value = "/admin/orders/{orderNo}/shipping")
    public String startShippingOrder(@PathVariable("orderNo") String orderNo,
                                     @RequestParam("version") long version) {
        try {
            startShippingService.startShipping(new StartShippingRequest(orderNo, version));
            return "admin/adminOrderShipped";
        } catch (OptimisticLockingFailureException e) {
            return "admin/adminOrderLockFail";
        }
    }
}
