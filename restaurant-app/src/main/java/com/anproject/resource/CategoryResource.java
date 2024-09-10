package com.anproject.resource;

import java.util.List;

import com.anproject.dto.request.CategoryRequestDTO;
import com.anproject.dto.response.CategoryResponseDTO;
import com.anproject.service.CategoryService;

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

@Path("/categories")
public class CategoryResource {

	@Inject
	CategoryService categoryService;

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveCategory(CategoryRequestDTO categoryRequestDto) {
		try {
			System.out.println(categoryRequestDto.getCategoryName() + " " + categoryRequestDto.getParentId());
			categoryService.saveCategory(categoryRequestDto);
			return Response.status(Response.Status.CREATED).entity(categoryRequestDto).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCategory(CategoryRequestDTO categoryRequestDto) {
		try {
			categoryService.updateCategory(categoryRequestDto);
			return Response.status(Response.Status.OK).entity(categoryRequestDto).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCategory(@PathParam("id") int id) {
		try {
			categoryService.deleteCategory(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/get-by-id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryById(@PathParam("id") int id) {
		try {
			CategoryResponseDTO category = categoryService.getCategoryById(id);
			 if (category == null) {
		            return Response.status(Response.Status.NOT_FOUND).build();
		        }
			return Response.status(Response.Status.OK).entity(category).build();
		} catch (Exception e) {
	        e.printStackTrace();

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategories() {
		try {
			List<CategoryResponseDTO> categories = categoryService.getAllCategories();
			return Response.status(Response.Status.OK).entity(categories).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
