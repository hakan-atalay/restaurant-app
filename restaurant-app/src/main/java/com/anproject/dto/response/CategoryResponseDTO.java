package com.anproject.dto.response;

import java.util.List;

import com.anproject.entity.Category;

public class CategoryResponseDTO {
	private int id;
	private String categoryName;
	private Integer parentId;
	private List<CategoryResponseDTO> subCategories;

	public CategoryResponseDTO() {
	}

	public CategoryResponseDTO(int id, String categoryName, Integer parentId, List<CategoryResponseDTO> subCategories) {
		this.id = id;
		this.categoryName = categoryName;
		this.parentId = parentId;
		this.subCategories = subCategories;
	}

	public static CategoryResponseDTO fromEntity(Category category) {
		List<CategoryResponseDTO> subCategories = category.getSubCategories().stream()
				.map(CategoryResponseDTO::fromEntity).toList();

		Integer parentId = (category.getParentCategory() != null) ? category.getParentCategory().getId() : null;

		return new CategoryResponseDTO(
				category.getId(),
				category.getCategoryName(),
				parentId,
				subCategories);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<CategoryResponseDTO> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<CategoryResponseDTO> subCategories) {
		this.subCategories = subCategories;
	}

}
