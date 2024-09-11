package com.anproject.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.anproject.dao.CategoryDAO;
import com.anproject.dao.ProductDAO;
import com.anproject.dto.request.ProductRequestDTO;
import com.anproject.dto.response.ProductResponseDTO;
import com.anproject.entity.Category;
import com.anproject.entity.Product;

import jakarta.inject.Inject;

public class ProductService {
	
	@Inject
	private ProductDAO productDao;

	@Inject
	private CategoryDAO categoryDao; 

	public void saveProduct(ProductRequestDTO productRequestDto) {
		Category category = categoryDao.getCategoryById(productRequestDto.getCategoryId()); 
		Product product = ProductRequestDTO.toEntity(productRequestDto, category);
		product.setCreated(new Date());
		productDao.saveProduct(product);
	}

	public void updateProduct(ProductRequestDTO productRequestDto) {
		Product existingProduct = productDao.getProductById(productRequestDto.getId());

		if (existingProduct != null) {
			Category category = categoryDao.getCategoryById(productRequestDto.getCategoryId()); 
			Product product = ProductRequestDTO.toEntity(productRequestDto, category);
			product.setCreated(existingProduct.getCreated());
			product.setUpdated(new Date());
			productDao.updateProduct(product);
		}
	}

	public void deleteProduct(int id) {
		productDao.deleteProduct(id);
	}

	public ProductResponseDTO getProductById(int id) {
		Product product = productDao.getProductById(id);
		return product != null ? ProductResponseDTO.fromEntity(product) : null;
	}

	public List<ProductResponseDTO> getAllProducts() {
		List<Product> productList = productDao.getAllProducts();
		return productList.stream().map(ProductResponseDTO::fromEntity).collect(Collectors.toList());
	}

	public void updateProductPhoto(int productId, String fileName) {
		Product product = productDao.getProductById(productId);
        if (product != null) {
            product.setPhotos(fileName);
            productDao.updateProduct(product);
        }
	}
	
}
