package com.anproject.dto.response;

import java.util.Date;

import com.anproject.entity.Product;

public class ProductResponseDTO {
	private int id;
	private String productName;
	private double price;
	private String photos;
	private CategoryResponseDTO categoryResponseDto;
	private Date created;
	private Date updated;

	public ProductResponseDTO() {
	}

	public ProductResponseDTO(int id, String productName, double price, String photos,
			CategoryResponseDTO categoryResponseDto, Date created, Date updated) {
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.photos = photos;
		this.categoryResponseDto = categoryResponseDto;
		this.created = created;
		this.updated = updated;
	}

	public static ProductResponseDTO fromEntity(Product product) {
		return new ProductResponseDTO(product.getId() ,product.getProductName(), product.getPrice(), product.getPhotos(),
				CategoryResponseDTO.fromEntity(product.getCategory()), product.getCreated(), product.getUpdated());
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

	public CategoryResponseDTO getCategoryResponseDto() {
		return categoryResponseDto;
	}

	public void setCategoryResponseDto(CategoryResponseDTO categoryResponseDto) {
		this.categoryResponseDto = categoryResponseDto;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
