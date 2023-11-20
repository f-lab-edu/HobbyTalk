package com.jongho.category.application.service;

import com.jongho.category.domain.model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllMainCategory();
    public List<Category> getSubCategory();
}
