package com.anproject.service;

import java.util.List;
import java.util.stream.Collectors;

import com.anproject.dao.CategoryDAO;
import com.anproject.dto.request.CategoryRequestDTO;
import com.anproject.dto.response.CategoryResponseDTO;
import com.anproject.entity.Category;

import jakarta.inject.Inject;

public class CategoryService {

	@Inject
	private CategoryDAO categoryDAO;

	public void saveCategory(CategoryRequestDTO categoryRequestDto) {
		Category parentCategory = null;
		if (categoryRequestDto.getParentId() != null) {
			parentCategory = categoryDAO.getCategoryById(categoryRequestDto.getParentId());
		}
		Category category = CategoryRequestDTO.toEntity(categoryRequestDto, parentCategory);
		categoryDAO.saveCategory(category);
	}

	public void updateCategory(CategoryRequestDTO categoryRequestDto) {
		Category existingCategory = categoryDAO.getCategoryById(categoryRequestDto.getId());

		if (existingCategory != null) {
			Category parentCategory = null;
			if (categoryRequestDto.getParentId() != null) {
				parentCategory = categoryDAO.getCategoryById(categoryRequestDto.getParentId());
			}
			Category updatedCategory = CategoryRequestDTO.toEntity(categoryRequestDto, parentCategory);
			updatedCategory.setId(existingCategory.getId());
			categoryDAO.updateCategory(updatedCategory);
		}
	}

	public void deleteCategory(int id) {
		categoryDAO.deleteCategory(id);
	}

	public CategoryResponseDTO getCategoryById(int id) {
		Category category = categoryDAO.getCategoryById(id);
		return CategoryResponseDTO.fromEntity(category);
	}

	public List<CategoryResponseDTO> getAllCategories() {
		List<Category> categories = categoryDAO.getAllCategories();
		return categories.stream().map(CategoryResponseDTO::fromEntity).collect(Collectors.toList());
	}

}
