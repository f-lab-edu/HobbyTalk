package com.jongho.category.domain.repository;

import com.jongho.category.domain.model.Category;

import java.util.List;

public interface CategoryRepository {
    public List<Category> selectMainCategory();
    public List<Category> selectSubCategory();
}
