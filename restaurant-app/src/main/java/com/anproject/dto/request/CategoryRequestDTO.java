package com.anproject.dto.request;

import com.anproject.entity.Category;

public class CategoryRequestDTO {
	private int id;
	private String categoryName;
	private Integer parentId;

	public CategoryRequestDTO() {}

	public static Category toEntity(CategoryRequestDTO categoryRequestDto, Category parentCategory) {
		Category category = new Category();
		category.setId(categoryRequestDto.getId());
		category.setCategoryName(categoryRequestDto.getCategoryName());
	    category.setParentCategory(parentCategory);
		return category;
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

}
