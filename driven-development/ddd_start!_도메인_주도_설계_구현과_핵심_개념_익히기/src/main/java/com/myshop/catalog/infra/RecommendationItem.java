package com.myshop.catalog.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendationItem {
    private String itemId;
    private String type;
    private String rank;
}
