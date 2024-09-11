package com.anproject.resource;

import java.io.InputStream;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.anproject.dto.request.ProductRequestDTO;
import com.anproject.dto.response.ProductResponseDTO;
import com.anproject.service.ProductService;
import com.anproject.util.FileUploadUtil;

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

@Path("/products")
public class ProductResource {

	@Inject
	private ProductService productService;
	
	@Inject
	private FileUploadUtil fileUploadUtil;

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveProduct(ProductRequestDTO productRequestDto) {
		try {
			productService.saveProduct(productRequestDto);
			return Response.status(Response.Status.CREATED).entity(productRequestDto).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Path("/upload-image/{productId}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadImage(
			@PathParam("productId") int productId,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetails) {
		try {
			System.out.println(fileDetails.getFileName()+ "*******************************************");
			System.out.println(System.getProperty("user.dir"));
			fileUploadUtil.saveFile(uploadedInputStream, fileDetails.getFileName());
			productService.updateProductPhoto(productId, fileDetails.getFileName());
			return Response.status(Response.Status.CREATED).entity(fileDetails.getFileName()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduct(ProductRequestDTO productRequestDto) {
		try {
			productService.updateProduct(productRequestDto);
			return Response.status(Response.Status.OK).entity(productRequestDto).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@PathParam("id") int id) {
		try {
			productService.deleteProduct(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/get-by-id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductById(@PathParam("id") int id) {
		try {
			ProductResponseDTO product = productService.getProductById(id);
			if (product != null) {
				return Response.status(Response.Status.OK).entity(product).build();
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
	public Response getAllProducts() {
		try {
			List<ProductResponseDTO> products = productService.getAllProducts();
			return Response.status(Response.Status.OK).entity(products).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}