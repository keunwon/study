package com.myshop.catalog.infra;

import com.myshop.catalog.command.domain.product.model.Product;
import com.myshop.catalog.command.domain.product.model.ProductId;
import com.myshop.catalog.command.domain.product.ProductRecommendationService;
import com.myshop.catalog.command.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RecSystemClient implements ProductRecommendationService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getRecommendationsOf(ProductId id) {
        List<RecommendationItem> items = getRecItems(id.getId());
        return toProducts(items);
    }

    private List<RecommendationItem> getRecItems(String itemId) {
        // http client 외분 연동
        return Collections.emptyList();
    }

    private List<Product> toProducts(List<RecommendationItem> items) {
        return items.stream()
                .map(item -> productRepository.findById(toProductId(item.getItemId())))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private ProductId toProductId(String itemId) {
        return new ProductId(itemId);
    }
}
