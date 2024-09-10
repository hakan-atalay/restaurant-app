package com.anproject.dto.response;


import com.anproject.entity.OrderItem;

public class OrderItemResponseDTO {
	private int id;
	private int productId;
	private double price;
	private int quantity;

	public OrderItemResponseDTO() {}

	public OrderItemResponseDTO(int id, int productId, double price, int quantity) {
		this.id = id;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}

	public static OrderItemResponseDTO fromEntity(OrderItem orderItem) {
		return new OrderItemResponseDTO(orderItem.getId(), orderItem.getProduct().getId(), orderItem.getPrice(),
				orderItem.getQuantity());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
