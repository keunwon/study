package com.keunwon.jpashop.controller;

import com.keunwon.jpashop.repository.OrderSearch;
import com.keunwon.jpashop.service.ItemService;
import com.keunwon.jpashop.service.MemberService;
import com.keunwon.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;


    @GetMapping(path = "/order")
    public String createForm(Model model) {
        model.addAttribute("members", memberService.findMembers());
        model.addAttribute("items", itemService.findItems());

        return "order/orderForm";
    }

    @PostMapping(path = "/order")
    public String order(@RequestParam Long memberId,
                        @RequestParam Long itemId,
                        @RequestParam int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping(path = "/orders")
    public String orderList(@ModelAttribute OrderSearch orderSearch, Model model) {
        model.addAttribute("orders", orderService.findOrders(orderSearch));

        return "order/orderList";
    }

    @PostMapping(path = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
