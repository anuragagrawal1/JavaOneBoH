package com.sbux.ebs.surveyforms.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sbux.ebs.surveyforms.business.UserBaoJPA;
import com.sbux.ebs.surveyforms.dto.User;
import com.sbux.ebs.surveyforms.exception.ErrorCode;
import com.sbux.ebs.surveyforms.exception.ExceptionManager;

@Path("/users")
public class UserService {

	@EJB
	UserBaoJPA userBAO;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllUsers() {
		try {
			List<User> reqUsers = userBAO.getAllUsers();

			if (reqUsers != null && !reqUsers.isEmpty()) {
				return Response.status(Status.OK).entity(new GenericEntity<List<User>>(reqUsers) {
				}).build();
			} else {
				return ExceptionManager.buildCustomErrorResponse(ErrorCode.NoRecordsFound, Status.BAD_REQUEST);
			}
		} catch (Throwable exception) {
			return ExceptionManager.buildErrorResponse(exception, Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Path("/{username}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUser(@PathParam("username") String username) {
		try {
			User reqUser = userBAO.getSingleUser(username);

			if (reqUser != null) {
				return Response.status(Status.OK).entity(reqUser).build();
			} else {
				return ExceptionManager.buildCustomErrorResponse(ErrorCode.NoRecordsFound, Status.BAD_REQUEST);
			}
		} catch (Throwable exception) {
			return ExceptionManager.buildErrorResponse(exception, Status.INTERNAL_SERVER_ERROR);
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createUser(User newUser) {
		try {
			boolean userCreated = userBAO.createUser(newUser);
			if (userCreated) {
				return Response.status(Status.ACCEPTED).build();
			} else {
				return ExceptionManager.buildCustomErrorResponse(ErrorCode.InValidOption, Status.BAD_REQUEST);
			}
		} catch (Throwable exception) {
			return ExceptionManager.buildErrorResponse(exception, Status.INTERNAL_SERVER_ERROR);
		}
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateUser(User updateUser) {
		try {
			boolean userUpdated = userBAO.updateUser(updateUser);

			if (userUpdated) {
				return Response.status(Status.ACCEPTED).build();
			} else {
				return ExceptionManager.buildCustomErrorResponse(ErrorCode.InValidOption, Status.BAD_REQUEST);
			}
		} catch (Throwable exception) {
			return ExceptionManager.buildErrorResponse(exception, Status.INTERNAL_SERVER_ERROR);
		}
	}

}
