package com.myshop.order.infra.paygate;

import com.myshop.order.command.application.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExternalRefundService implements RefundService {

    @Override
    public void refund(String orderNumber) {
        log.info("refund order[{}]", orderNumber);
    }
}
