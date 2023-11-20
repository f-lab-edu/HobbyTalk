package com.jongho.category.dao.repository;

import com.jongho.category.dao.mapper.CategoryMapper;
import com.jongho.category.domain.model.Category;
import com.jongho.category.domain.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryMyBatisRepositoryImpl implements CategoryRepository {
    private final CategoryMapper categoryMapper;

    public List<Category> selectMainCategory() {
        return categoryMapper.selectMainCategory();
    }

    public List<Category> selectSubCategory() {
        return categoryMapper.selectSubCategory();
    }
}
