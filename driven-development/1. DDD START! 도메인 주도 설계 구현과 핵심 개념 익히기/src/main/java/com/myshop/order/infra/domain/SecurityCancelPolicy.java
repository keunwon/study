package com.myshop.order.infra.domain;

import com.myshop.order.command.domain.CancelPolicy;
import com.myshop.order.command.domain.Canceller;
import com.myshop.order.command.domain.model.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SecurityCancelPolicy implements CancelPolicy {
    @Override
    public boolean hasCancellationPermission(Order order, Canceller canceller) {
        return false;
    }

    private boolean isCancellerOrderer(Order order, Canceller canceller) {
        return order.getOrderer().getMemberId().getId().equals(canceller.getMemberId());
    }

    private boolean isCurrentUserAdminRole() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) { return false; }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) { return false; }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities == null) { return false; }

        return authorities.stream()
                .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()));
    }
}
