package com.ip241k.skoryk.resource;

import com.ip241k.skoryk.dao.UserDAO;
import com.ip241k.skoryk.model.User;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDAO userDAO;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GET
    public Response getAllUsers() {
        try {
            List<User> users = userDAO.getAllUsers();
            return Response.ok(users).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") int id) {
        try {
            User user = userDAO.getUserById(id);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\":\"User not found\"}")
                        .build();
            }
            return Response.ok(user).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        try {
            userDAO.createUser(user);
            return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, User user) {
        try {
            user.setId(id);
            userDAO.updateUser(user);
            return Response.ok(user).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        try {
            userDAO.deleteUser(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
