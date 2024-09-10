package com.anproject.dto.request;

import java.util.List;

import com.anproject.entity.AppUser;
import com.anproject.entity.Order;

public class OrderRequestDTO {
	private int id;
	private int userId;
	private boolean status;
	private List<OrderItemRequestDTO> orderItems;

	public OrderRequestDTO() {
	}

	public static Order toEntity(OrderRequestDTO orderRequestDto, AppUser user) {
		Order order = new Order();
		order.setId(orderRequestDto.getId());
		order.setUser(user);
		order.setStatus(orderRequestDto.isStatus());
		return order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<OrderItemRequestDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemRequestDTO> orderItems) {
		this.orderItems = orderItems;
	}

}