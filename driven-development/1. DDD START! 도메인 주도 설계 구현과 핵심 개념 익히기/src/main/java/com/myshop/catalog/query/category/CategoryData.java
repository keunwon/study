package com.myshop.catalog.query.category;

import com.myshop.catalog.command.domain.category.CategoryId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryData {
    @EmbeddedId
    private CategoryId id;

    @Column(name = "name")
    private String name;
}
