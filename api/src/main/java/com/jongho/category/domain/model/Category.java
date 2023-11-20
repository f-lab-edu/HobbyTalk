package com.jongho.category.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Category {
    private Long id;
    private final String name;
    private final Long parentId;
}
