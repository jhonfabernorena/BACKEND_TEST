package app.controller;

import java.util.Map;
import java.util.List;

import app.model.Product;
import app.service.ProductService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService service;

    @GET
    public List<Product> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Product p = service.get(id);
        if (p == null) {
            return Response.status(404).entity(errorJson("Not found")).build();
        }
        return Response.ok(p).build();
    }

    @POST
    public Response create(@Valid Product product) {
        return Response.status(201).entity(service.create(product)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        if (!service.delete(id)) {
            return Response.status(404).entity(errorJson("Not found")).build();
        }
        return Response.ok(successJson("Product deleted successfully")).build();
    }

    private Map<String, Object> errorJson(String msg) {
        return Map.of("code", 404, "message", msg);
    }

    private Map<String, Object> successJson(String msg) {
        return Map.of("code", 200, "message", msg);
    }
}
