package com.jongho.category.application.service;

import com.jongho.category.domain.model.Category;
import com.jongho.category.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> getMainCategory() {

        return categoryRepository.selectMainCategory();
    }

    @Override
    public List<Category> getSubCategory(Long parentId) {

        return categoryRepository.selectSubCategory(parentId);
    }
}
