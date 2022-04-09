package com.myshop.order.ui;

import com.myshop.order.command.application.CancelOrderService;
import com.myshop.order.command.domain.Canceller;
import com.myshop.order.command.domain.model.OrderNo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.plaf.SeparatorUI;

@RequiredArgsConstructor
@Controller
public class CancelOrderController {
    private final CancelOrderService cancelOrderService;

    @GetMapping(value = "/my/orders/{orderNo}/cancel")
    public String orderDetail(@PathVariable("orderNo") String orderNo, ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cancelOrderService.cancel(new OrderNo(orderNo), new Canceller(user.getUsername()));
        return "my/orderCanceled";
    }
}
