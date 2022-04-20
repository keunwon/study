package com.circuit.springcircuitbreaker.api;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("scrap")
public class RestDto {
    private String title;
    private String url;
    private String type;
    private String image;
    private String description;
    private String author;

    public RestDto() {};

    public RestDto(String title, String url, String type, String image, String description, String author) {
        this.title = title;
        this.url = url;
        this.type = type;
        this.image = image;
        this.description = description;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
