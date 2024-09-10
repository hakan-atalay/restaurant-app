package com.anproject.service;

import java.util.List;
import java.util.stream.Collectors;

import com.anproject.dao.OrderItemDAO;
import com.anproject.dao.ProductDAO;
import com.anproject.dto.request.OrderItemRequestDTO;
import com.anproject.dto.response.OrderItemResponseDTO;
import com.anproject.entity.OrderItem;
import com.anproject.entity.Product;

import jakarta.inject.Inject;

public class OrderItemService {

	@Inject
	private OrderItemDAO orderItemDao;

	@Inject
	private ProductDAO productDao;

	public void saveOrderItem(OrderItemRequestDTO orderItemRequestDto) {
		Product product = productDao.getProductById(orderItemRequestDto.getProductId());
		OrderItem orderItem = new OrderItem();
		orderItem.setProduct(product);
		orderItem.setQuantity(orderItemRequestDto.getQuantity());
		orderItem.setPrice(product.getPrice() * orderItemRequestDto.getQuantity());
		orderItemDao.saveOrderItem(orderItem);
	}

	public void updateOrderItem(OrderItemRequestDTO orderItemRequestDto) {
		OrderItem existingOrderItem = orderItemDao.getOrderItemById(orderItemRequestDto.getId());
		if (existingOrderItem != null) {
			Product product = productDao.getProductById(orderItemRequestDto.getProductId());
			existingOrderItem.setProduct(product);
			existingOrderItem.setQuantity(orderItemRequestDto.getQuantity());
			existingOrderItem.setPrice(product.getPrice() * orderItemRequestDto.getQuantity());
			orderItemDao.updateOrderItem(existingOrderItem);
		}
	}

	public void deleteOrderItem(int id) {
		orderItemDao.deleteOrderItem(id);
	}

	public OrderItemResponseDTO getOrderItemById(int id) {
		OrderItem orderItem = orderItemDao.getOrderItemById(id);
		return orderItem != null ? OrderItemResponseDTO.fromEntity(orderItem) : null;
	}

	public List<OrderItemResponseDTO> getAllOrderItems() {
		List<OrderItem> orderItems = orderItemDao.getAllOrderItems();
		return orderItems.stream().map(OrderItemResponseDTO::fromEntity).collect(Collectors.toList());
	}

}
