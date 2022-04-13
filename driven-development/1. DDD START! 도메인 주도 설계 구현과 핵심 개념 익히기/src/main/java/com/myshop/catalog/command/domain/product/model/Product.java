package com.myshop.catalog.command.domain.product.model;

import com.myshop.catalog.command.domain.category.CategoryId;
import com.myshop.common.jpa.converter.MoneyConverter;
import com.myshop.common.model.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product")
public class Product {

    @EmbeddedId
    private ProductId id;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"))
    private Set<CategoryId> categoryIds = new HashSet<>();

    private String name;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    private String detail;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "product_id")
    @OrderColumn(name = "list_idx")
    private List<Image> images = new ArrayList<>();

    public Product(ProductId id, String name, Money price, String detail, List<Image> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.images.addAll(images);
    }

    public void changeImages(List<Image> newImages) {
        images.clear();
        images.addAll(newImages);
    }

    public String getFirstImageThumbnailPath() {
        if (images == null || images.isEmpty()) { return null; }
        return images.get(0).getThumbnailURL();
    }
}
