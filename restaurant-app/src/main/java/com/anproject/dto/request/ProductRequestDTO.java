package com.anproject.dto.request;

import com.anproject.entity.Category;
import com.anproject.entity.Product;

public class ProductRequestDTO {
	private int id;
	private String productName;
	private double price;
	private String photos;
	private int categoryId;

	public ProductRequestDTO() {
	}

	public static Product toEntity(ProductRequestDTO productRequestDto, Category category) {
		Product product = new Product();
		product.setId(productRequestDto.getId());
		product.setProductName(productRequestDto.getProductName());
		product.setPrice(productRequestDto.getPrice());
		product.setPhotos(productRequestDto.getPhotos());
		product.setCategory(category);
		return product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
