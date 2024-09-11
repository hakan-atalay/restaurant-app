package com.anproject.resource;

import java.util.List;

import com.anproject.dto.request.OrderItemRequestDTO;
import com.anproject.dto.response.OrderItemResponseDTO;
import com.anproject.service.OrderItemService;

import jakarta.annotation.security.RolesAllowed;
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

@Path("order-items")
public class OrderItemResource {

	@Inject
	private OrderItemService orderItemService;

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"admin", "regular_user"})
	public Response saveOrderItem(OrderItemRequestDTO orderItemRequestDto) {
		try {
			orderItemService.saveOrderItem(orderItemRequestDto);
			return Response.status(Response.Status.CREATED).entity(orderItemRequestDto).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"admin", "regular_user"})
	public Response updateOrderItem(OrderItemRequestDTO orderItemRequestDto) {
		try {
			orderItemService.updateOrderItem(orderItemRequestDto);
			return Response.status(Response.Status.OK).entity(orderItemRequestDto).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"admin", "regular_user"})
	public Response deleteOrderItem(@PathParam("id") int id) {
		try {
			orderItemService.deleteOrderItem(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/get-by-id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"admin", "regular_user"})
	public Response getOrderItemById(@PathParam("id") int id) {
		try {
			OrderItemResponseDTO orderItem = orderItemService.getOrderItemById(id);
			if (orderItem != null) {
				return Response.status(Response.Status.OK).entity(orderItem).build();
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
	@RolesAllowed({"admin", "regular_user"})
	public Response getAllOrderItems() {
		try {
			List<OrderItemResponseDTO> orderItems = orderItemService.getAllOrderItems();
			return Response.status(Response.Status.OK).entity(orderItems).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
