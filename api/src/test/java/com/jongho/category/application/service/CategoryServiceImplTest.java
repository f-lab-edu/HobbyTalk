package com.jongho.category.application.service;

import com.jongho.category.application.service.CategoryServiceImpl;
import com.jongho.category.domain.model.Category;
import com.jongho.category.domain.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CategoryServiceImpl 클래스")
public class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Nested
    @DisplayName("getMainCategory 메소드는")
    class Describe_getMainCategory {

        @Test
        @DisplayName("CategoryRepository.selectMainCategory()을 한번 호출하고 반환받은 값을 반환한다.")
        void it_calls_CategoryRepository_selectMainCategory() {
            // given
            List<Category> expectCategories = List.of(new Category(1L, "main", 2L));
            when(categoryRepository.selectMainCategory()).thenReturn(expectCategories);

            // when
            categoryServiceImpl.getMainCategory();

            // then
            verify(categoryRepository, times(1)).selectMainCategory();
            assertEquals(expectCategories, categoryServiceImpl.getMainCategory());
        }
    }

    @Nested
    @DisplayName("getSubCategory 메소드는")
    class Describe_getSubCategory {
        @Test
        @DisplayName("CategoryRepository.selectSubCategory()을 한번 호출하고 반환받은 값을 반환한다.")
        void it_calls_CategoryRepository_selectSubCategory() {
            // given
            Long parentId = 1L;
            List<Category> expectCategories = List.of(new Category(1L, "main", 2L));
            when(categoryRepository.selectSubCategory(parentId)).thenReturn(expectCategories);

            // when
            categoryServiceImpl.getSubCategory(parentId);

            // then
            verify(categoryRepository, times(1)).selectSubCategory(parentId);
            assertEquals(expectCategories, categoryServiceImpl.getSubCategory(parentId));
        }
    }
}
