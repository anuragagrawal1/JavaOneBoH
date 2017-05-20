package com.sbux.ebs.surveyforms.rest;

import com.sbux.ebs.surveyforms.business.RoleBaoJPA;
import com.sbux.ebs.surveyforms.dto.Role;
import com.sbux.ebs.surveyforms.exception.ErrorCode;
import com.sbux.ebs.surveyforms.exception.ExceptionManager;

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

import org.apache.commons.collections.CollectionUtils;

@Path("/roles")
public class RoleService {

	@EJB
	RoleBaoJPA roleBAO;

	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllRoles() {
		try {
			List<Role> reqRoles = roleBAO.getAllRoles();

			if (CollectionUtils.isNotEmpty(reqRoles)) {
				return Response.status(Status.OK).entity(new GenericEntity<List<Role>>(reqRoles) {
				}).build();
			} else {
				return ExceptionManager.buildCustomErrorResponse(ErrorCode.NoRecordsFound, Status.BAD_REQUEST);
			}
		} catch (Throwable exception) {
			return ExceptionManager.buildErrorResponse(exception, Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Path("/{roleName}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getRole(@PathParam("roleName") String rolename) {
		try {
			Role reqrole = roleBAO.getSingleRole(rolename);

			if (reqrole != null) {
				return Response.status(Status.OK).entity(reqrole).build();
			} else {
				return ExceptionManager.buildCustomErrorResponse(ErrorCode.NoRecordsFound, Status.BAD_REQUEST);
			}
		} catch (Throwable exception) {
			return ExceptionManager.buildErrorResponse(exception, Status.INTERNAL_SERVER_ERROR);
		}
	}

	@POST
	@Path("/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createRole(Role newRole) {
		try {
			boolean roleCreated = roleBAO.createRole(newRole);

			if (roleCreated) {
				return Response.status(Status.ACCEPTED).build();
			} else {
				return ExceptionManager.buildCustomErrorResponse(ErrorCode.InValidOption, Status.BAD_REQUEST);
			}
		} catch (Throwable exception) {
			return ExceptionManager.buildErrorResponse(exception, Status.INTERNAL_SERVER_ERROR);
		}
	}

	@PUT
	@Path("/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateRole(Role updateRole) {
		try {
			boolean roleUpdated = roleBAO.updateRole(updateRole);

			if (roleUpdated) {
				return Response.status(Status.ACCEPTED).build();
			} else {
				return ExceptionManager.buildCustomErrorResponse(ErrorCode.InValidOption, Status.BAD_REQUEST);
			}
		} catch (Throwable exception) {
			return ExceptionManager.buildErrorResponse(exception, Status.INTERNAL_SERVER_ERROR);
		}
	}

}
