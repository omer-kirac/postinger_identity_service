package com.kafein.intern.identity.model;

import lombok.Data;

@Data
public class Post {
    private String id;
    private Long userId;
    private String description;
    private String imageUrl;
}
