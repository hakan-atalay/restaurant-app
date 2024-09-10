package com.anproject.dto.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.anproject.entity.Order;

public class OrderResponseDTO {
	private int id;
	private AppUserResponseDTO userResponseDto;
	private double totalPrice;
	private Date orderDate;
	private boolean status;
	private List<OrderItemResponseDTO> orderItems;

	public OrderResponseDTO() {
	}

	public OrderResponseDTO(int id, AppUserResponseDTO userResponseDto, double totalPrice, Date orderDate,
			boolean status, List<OrderItemResponseDTO> orderItems) {
		this.id = id;
		this.userResponseDto = userResponseDto;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.status = status;
		this.orderItems = orderItems;
	}

	public static OrderResponseDTO fromEntity(Order order) {
		return new OrderResponseDTO(order.getId(), AppUserResponseDTO.fromEntity(order.getUser()),
				order.getTotalPrice(), order.getOrderDate(), order.isStatus(),
				order.getOrderItems().stream().map(OrderItemResponseDTO::fromEntity).collect(Collectors.toList()));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AppUserResponseDTO getUserResponseDto() {
		return userResponseDto;
	}

	public void setUserResponseDto(AppUserResponseDTO userResponseDto) {
		this.userResponseDto = userResponseDto;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<OrderItemResponseDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemResponseDTO> orderItems) {
		this.orderItems = orderItems;
	}

}
