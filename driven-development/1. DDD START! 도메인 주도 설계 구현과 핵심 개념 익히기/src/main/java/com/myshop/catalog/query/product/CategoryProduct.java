package com.myshop.catalog.query.product;

import com.myshop.catalog.query.category.CategoryData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CategoryProduct {
    private final CategoryData category;
    private final List<ProductSummary> items;
    private final int page;
    private final int size;
    private final long totalCount;
    private final int totalPages;
}
