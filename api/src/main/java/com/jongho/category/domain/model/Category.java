package com.jongho.category.domain.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class Category {
    private String id;
    private final String name;
    private final String parentId;
}
