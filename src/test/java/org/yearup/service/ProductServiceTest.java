package org.yearup.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;
    //Create fake data
    Product product1 = new Product(1,"CRTL",25.0,1,"Sza Album","R&B",20,false,"image");
    Product product2 = new Product(2,"S.O.S",20.0,2,"Sza Album","R&B",15,false,"image");
    Product product3 = new Product(3,"Head Phones",30.0,3,"Ear buds","Red",30,true,"image");

    List<Product> listOfProducts = new ArrayList<>();
    @Test
    void findById_MissingId_ReturnsNull() {
// Arrange - script the mock: asked for id 99, hand back empty
        when(productRepository.findById(1)).thenReturn(Optional.empty());
// Act
        Product found = productService.getById(1);
// Assert
        assertNull(found);
    }
    @Test
    void findbyId_ProductId_ReturnsTheProduct() {
// Arrange
        when(productRepository.findById(1)).thenReturn(Optional.of(product1));
// Act
        Product found = productService.getById(1);
// Assert
        assertEquals(1, found.getProductId());
    }
    @Test
    void search_WithNoFilters_ReturnsAllProduct(){
        //Arrange
        listOfProducts.add(product1);
        listOfProducts.add(product2);
        listOfProducts.add(product3);
        when(productRepository.findAll()).thenReturn(listOfProducts);
        //Act
        List<Product> found = productService.search(null, null, null, null);
        //Assert
        assertNotNull(found);
        assertEquals(3, found.size());

    }
    @Test
    void updateProduct_ReturnUpdateStock(){
        //Arrange
        product1.setStock(50);
        product2.setStock(100);
        when(productRepository.findById(1)).thenReturn(Optional.of(product1));
        when(productRepository.save(any(Product.class))).thenReturn(product1);
        //Act
        Product found = productService.update(1, product2);
        //Assert
        assertNotNull(found);
        assertEquals(100, found.getStock());
    }
}