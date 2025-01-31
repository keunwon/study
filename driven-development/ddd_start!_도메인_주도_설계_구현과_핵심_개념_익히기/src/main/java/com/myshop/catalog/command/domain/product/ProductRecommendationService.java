package com.myshop.catalog.command.domain.product;

import com.myshop.catalog.command.domain.product.model.Product;
import com.myshop.catalog.command.domain.product.model.ProductId;

import java.util.List;

public interface ProductRecommendationService {
    List<Product> getRecommendationsOf(ProductId id);
}
