package com.myshop.catalog.command.domain.product.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("II")
public class InternalImage extends Image {

    public InternalImage(String path) {
        super(path);
    }

    @Override
    public String getURL() {
        return "/images/original/" + getPath();
    }

    @Override
    public boolean hasThumbnail() {
        return true;
    }

    @Override
    public String getThumbnailURL() {
        return "/images/thumbnail/" + getPath();
    }
}
