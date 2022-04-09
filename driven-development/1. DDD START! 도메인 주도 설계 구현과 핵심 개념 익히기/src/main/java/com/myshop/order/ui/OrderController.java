package com.myshop.order.ui;

import com.myshop.catalog.query.product.ProductData;
import com.myshop.catalog.query.product.ProductQueryService;
import com.myshop.common.ValidationErrorException;
import com.myshop.member.command.domain.model.MemberId;
import com.myshop.order.command.application.NoOrderProductException;
import com.myshop.order.command.application.OrderProduct;
import com.myshop.order.command.application.OrderRequest;
import com.myshop.order.command.application.PlaceOrderService;
import com.myshop.order.command.domain.OrdererService;
import com.myshop.order.command.domain.model.OrderNo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final ProductQueryService productQueryService;
    private final PlaceOrderService placeOrderService;
    private final OrdererService ordererService;

    @PostMapping(value = "/orders/orderConfirm")
    public String orderconfirm(@ModelAttribute("orderReq") OrderRequest orderRequest, ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderRequest.setOrdererMemberId(new MemberId(user.getUsername()));
        populateProductsAndTotalAmountsModel(orderRequest, modelMap);
        return "order/confirm";
    }

    @PostMapping(value = "/orders/order")
    public String order(@ModelAttribute("orderReq") OrderRequest orderRequest, BindingResult bindingResult, ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderRequest.setOrdererMemberId(new MemberId(user.getUsername()));

        try {
            OrderNo orderNo = placeOrderService.placeOrder(orderRequest);
            modelMap.addAttribute("orderNo", orderNo.getNumber());
            return "order/orderComplete";
        } catch (ValidationErrorException e) {
            e.getErrors().forEach(err -> {
                if (err.hasName()) {
                    bindingResult.rejectValue(err.getName(), err.getCode());
                } else {
                    bindingResult.reject(err.getCode());
                }
            });

            populateProductsAndTotalAmountsModel(orderRequest, modelMap);
            return "order/confirm";
        }
    }

    private void populateProductsAndTotalAmountsModel(OrderRequest orderRequest, ModelMap modelMap) {
        List<ProductData> products = getProducts(orderRequest.getOrderProducts());
        int totalAmounts = 0;

        for (int i = 0; i < orderRequest.getOrderProducts().size(); i++) {
            OrderProduct orderProduct = orderRequest.getOrderProducts().get(i);
            ProductData productData = products.get(i);
            totalAmounts += orderProduct.getQuantity() * productData.getPrice().getValue();
        }

        modelMap.addAttribute("products", products);
        modelMap.addAttribute("totalAmounts", totalAmounts);
    }

    private List<ProductData> getProducts(List<OrderProduct> orderProducts) {
        List<ProductData> results = new ArrayList<>();

        for (OrderProduct product : orderProducts) {
            ProductData productData = productQueryService.getProduct(product.getProductId())
                    .orElseThrow(() -> new NoOrderProductException(product.getProductId()));
            results.add(productData);
        }
        return results;
    }

    @ExceptionHandler(NoOrderProductException.class)
    public String handleNoOrderProduct() {
        return "order/noProduct";
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }
}
