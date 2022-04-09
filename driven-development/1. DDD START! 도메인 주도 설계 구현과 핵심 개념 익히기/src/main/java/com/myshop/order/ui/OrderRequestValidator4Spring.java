package com.myshop.order.ui;

import com.myshop.order.command.application.OrderProduct;
import com.myshop.order.command.application.OrderRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

public class OrderRequestValidator4Spring implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return OrderRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrderRequest orderReq = (OrderRequest) target;

        if (CollectionUtils.isEmpty(orderReq.getOrderProducts())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderProducts", "required");
        } else {
            for (int i = 0; i < orderReq.getOrderProducts().size(); i++) {
                OrderProduct orderProduct = orderReq.getOrderProducts().get(i);

                if (StringUtils.isEmpty(orderProduct.getProductId())) {
                    errors.rejectValue("orderProducts[%d].productId".formatted(i), "required");
                }
                if (orderProduct.getQuantity() <= 0) {
                    errors.rejectValue("orderProducts[%d].quantity".formatted(i), "nonPositive");
                }
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.receiver.name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.receiver.phone", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.zipCode", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.address1", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.address2", "required");
    }
}
