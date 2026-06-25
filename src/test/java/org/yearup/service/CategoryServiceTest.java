package org.yearup.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.repository.CategoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks // wired to real CategoryService class
    private CategoryService categoryService;
    @Test
    void findById_MissingId_ReturnsNull(){
        //Arrange
        when(categoryRepository.findById(4)).thenReturn(Optional.empty());
        //Act
        Category found = categoryService.getById(4);
        //Assert
        assertNull(found);
    }
    @Test
    void findById_CategoryId_ReturnsCategory(){
        //Arrange
        Category category = new Category(4,"Example Name", "Example Description");
        when(categoryRepository.findById(4)).thenReturn(Optional.of(category));
        //Act
        Category found = categoryService.getById(4);
        //Assert
        assertNotNull(found);
        assertEquals(4,found.getCategoryId());
        assertEquals("Example Name", found.getName());
        assertEquals("Example Description", found.getDescription());
    }
}