package com.myshop.catalog.query.product;

import com.myshop.catalog.NoCategoryException;
import com.myshop.catalog.domain.category.CategoryId;
import com.myshop.catalog.domain.product.ProductId;
import com.myshop.catalog.query.category.CategoryData;
import com.myshop.catalog.query.category.CategoryDataDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductQueryService {
    private final ProductDataDao productDataDao;
    private final CategoryDataDao categoryDataDao;

    @Transactional
    public CategoryProduct getProductInCategory(Long categoryId, int page, int size) {
        CategoryData category = categoryDataDao.findById(CategoryId.of(categoryId))
                .orElseThrow(NoCategoryException::new);

        Page<ProductData> productPage = productDataDao.findByCategoryIdsContains(category.getId(), Pageable.ofSize(size).withPage(page - 1));
        return new CategoryProduct(
                category,
                toSummary(productPage.getContent()),
                page,
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages());
    }

    public Optional<ProductData> getProduct(String productId) {
        return productDataDao.findById(new ProductId(productId));
    }

    private List<ProductSummary> toSummary(List<ProductData> products) {
        return products.stream()
                .map(x -> new ProductSummary(
                        x.getId().getId(),
                        x.getName(),
                        x.getPrice().getValue(),
                        x.getFirstImageThumbnailPath()))
                .toList();
    }
}
