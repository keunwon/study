package com.myshop.order.ui;

import com.myshop.common.user.LoginUser;
import com.myshop.order.query.application.OrderDetail;
import com.myshop.order.query.application.OrderDetailService;
import com.myshop.order.query.dao.OrderSummaryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MyOrderController {
    private final OrderDetailService orderDetailService;
    private final OrderSummaryDao orderSummaryDao;

    @GetMapping(value = "/my/orders")
    public String orders(@LoginUser User user, ModelMap modelMap) {
        modelMap.addAttribute("orders", orderSummaryDao.findByOrdererId(user.getUsername()));
        return "my/orders";
    }

    @GetMapping(value = "/my/orders/{orderNo}")
    public String orderDetail(@PathVariable("orderNo") String orderNo, @LoginUser User user, ModelMap modelMap) {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetail(orderNo);

        if (orderDetail.isPresent()) {
            if (orderDetail.get().getOrderer().getMemberId().getId().equals(user.getUsername())) {
                modelMap.addAttribute("order", orderDetail.get());
                return "my/orderDetail";
            }
            return "my/notYourOrder";
        }
        return "my/noOrder";
    }
}
