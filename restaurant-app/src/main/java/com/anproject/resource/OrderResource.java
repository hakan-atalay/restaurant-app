package com.anproject.resource;

import java.util.List;

import com.anproject.dto.request.OrderRequestDTO;
import com.anproject.dto.response.OrderResponseDTO;
import com.anproject.service.OrderService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("orders")
public class OrderResource {

	@Inject
	private OrderService orderService;

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveOrder(OrderRequestDTO orderRequestDto) {
		try {
			orderService.saveOrder(orderRequestDto);
			return Response.status(Response.Status.CREATED).entity(orderRequestDto).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOrder(OrderRequestDTO orderRequestDto) {
		try {
			orderService.updateOrder(orderRequestDto);
			return Response.status(Response.Status.OK).entity(orderRequestDto).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOrder(@PathParam("id") int id) {
		try {
			orderService.deleteOrder(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/get-by-id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderById(@PathParam("id") int id) {
		try {
			OrderResponseDTO order = orderService.getOrderById(id);
			if (order != null) {
				return Response.status(Response.Status.OK).entity(order).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrders() {
		try {
			List<OrderResponseDTO> orders = orderService.getAllOrders();
			return Response.status(Response.Status.OK).entity(orders).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
