package com.anproject.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.anproject.dao.AppUserDAO;
import com.anproject.dao.OrderDAO;
import com.anproject.dao.OrderItemDAO;
import com.anproject.dao.ProductDAO;
import com.anproject.dto.request.OrderRequestDTO;
import com.anproject.dto.response.OrderResponseDTO;
import com.anproject.entity.AppUser;
import com.anproject.entity.Order;
import com.anproject.entity.OrderItem;
import com.anproject.entity.Product;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public class OrderService {

	@Inject
	private OrderDAO orderDao;

	@Inject
	private OrderItemDAO orderItemDao;

	@Inject
	private AppUserDAO userDao;

	@Inject
	private ProductDAO productDao;

	@Transactional
	public void saveOrder(OrderRequestDTO orderRequestDto) {
		AppUser user = userDao.getAppUserById(orderRequestDto.getUserId());
		Order order = new Order();
		order.setUser(user);
		order.setOrderDate(new Date());
		order.setStatus(false);
		orderDao.saveOrder(order);

		if (orderRequestDto.getOrderItems() != null && !orderRequestDto.getOrderItems().isEmpty()) {
			List<OrderItem> orderItems = orderRequestDto.getOrderItems().stream().map(orderItemDto -> {
				Product product = productDao.getProductById(orderItemDto.getProductId());
				OrderItem orderItem = new OrderItem();
				orderItem.setProduct(product);
				orderItem.setQuantity(orderItemDto.getQuantity());
				orderItem.setPrice(product.getPrice() * orderItem.getQuantity());
				orderItem.setOrder(order);
				return orderItem;
			}).collect(Collectors.toList());

			double totalPrice = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
			order.setTotalPrice(totalPrice);
			order.setOrderItems(orderItems);

			orderDao.updateOrder(order);
			orderItems.forEach(orderItemDao::saveOrderItem);
		}
	}

	@Transactional
	public void updateOrder(OrderRequestDTO orderRequestDto) {
		Order existingOrder = orderDao.getOrderById(orderRequestDto.getId());

		if (existingOrder != null) {
			AppUser user = userDao.getAppUserById(orderRequestDto.getUserId());
			existingOrder.setUser(user);
			existingOrder.setStatus(orderRequestDto.isStatus());

			existingOrder.getOrderItems().forEach(item -> orderItemDao.deleteOrderItem(item.getId()));

			if (orderRequestDto.getOrderItems() != null && !orderRequestDto.getOrderItems().isEmpty()) {
				List<OrderItem> newOrderItems = orderRequestDto.getOrderItems().stream().map(orderItemDto -> {
					Product product = productDao.getProductById(orderItemDto.getProductId());
					OrderItem orderItem = new OrderItem();
					orderItem.setProduct(product);
					orderItem.setQuantity(orderItemDto.getQuantity());
					orderItem.setPrice(product.getPrice() * orderItem.getQuantity());
					orderItem.setOrder(existingOrder);
					return orderItem;
				}).collect(Collectors.toList());

				double totalPrice = newOrderItems.stream().mapToDouble(OrderItem::getPrice).sum();
				existingOrder.setTotalPrice(totalPrice);
				existingOrder.setOrderItems(newOrderItems);

				orderDao.updateOrder(existingOrder);
				newOrderItems.forEach(orderItemDao::saveOrderItem);
			}
		}
	}

	public void deleteOrder(int id) {
		Order order = orderDao.getOrderById(id);
		if (order != null) {
			order.getOrderItems().forEach(item -> orderItemDao.deleteOrderItem(item.getId()));
			orderDao.deleteOrder(id);
		}
	}

	public OrderResponseDTO getOrderById(int id) {
		Order order = orderDao.getOrderById(id);
		return order != null ? OrderResponseDTO.fromEntity(order) : null;
	}

	public List<OrderResponseDTO> getAllOrders() {
		List<Order> orderList = orderDao.getAllOrders();
		return orderList.stream().map(OrderResponseDTO::fromEntity).collect(Collectors.toList());
	}

}
