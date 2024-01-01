package com.jongho.category.application.service;

import com.jongho.category.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> getMainCategory();
    public List<Category> getSubCategory(Long parentId);
    public Optional<Category> getOneCategoryById(Long categoryId);
}
