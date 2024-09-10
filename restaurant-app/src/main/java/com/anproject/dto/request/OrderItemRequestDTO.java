package com.anproject.dto.request;


import com.anproject.entity.Order;
import com.anproject.entity.OrderItem;
import com.anproject.entity.Product;

public class OrderItemRequestDTO {
	private int id;
	private int productId;
	private int quantity;
	private double price;

	public OrderItemRequestDTO() {}

	public static OrderItem toEntity(OrderItemRequestDTO orderItemRequestDto, Order order, Product product) {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(orderItemRequestDto.getId());
		orderItem.setProduct(product);
		orderItem.setQuantity(orderItemRequestDto.getQuantity());
		orderItem.setPrice(orderItemRequestDto.getPrice());
		orderItem.setOrder(order);
		return orderItem;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
