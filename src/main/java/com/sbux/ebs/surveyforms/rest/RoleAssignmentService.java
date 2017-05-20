package com.sbux.ebs.surveyforms.rest;

import com.sbux.ebs.surveyforms.business.RoleAssignmentBaoJPA;
import com.sbux.ebs.surveyforms.dto.RoleAssignment;
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

@Path("/roleAssignments")
public class RoleAssignmentService {

	@EJB
	RoleAssignmentBaoJPA reqRoleAssignmentsBAO;

	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllRoleAssignments() {
		try {
			List<RoleAssignment> reqRoleAssignments = reqRoleAssignmentsBAO.getAllRoleAssignments();

			if (CollectionUtils.isNotEmpty(reqRoleAssignments)) {

				return Response.status(Status.OK).entity(new GenericEntity<List<RoleAssignment>>(reqRoleAssignments) {
				}).build();

			} else {
				return ExceptionManager.buildCustomErrorResponse(ErrorCode.NoRecordsFound, Status.BAD_REQUEST);
			}
		} catch (Throwable exception) {
			return ExceptionManager.buildErrorResponse(exception, Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Path("/{userName}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getRoleAssignment(@PathParam("userName") String username) {
		try {
			List<RoleAssignment> reqRoleAssignments = reqRoleAssignmentsBAO.getSingleRoleAssignment(username);

			if (CollectionUtils.isNotEmpty(reqRoleAssignments)) {
				return Response.status(Status.OK).entity(new GenericEntity<List<RoleAssignment>>(reqRoleAssignments) {
				}).build();
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
	public Response createRoleAssignment(RoleAssignment newRoleAssignment) {
		try {
			boolean roleCreated = reqRoleAssignmentsBAO.createRoleAssignment(newRoleAssignment);
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
	public Response updateRoleAssignment(RoleAssignment updateRoleAssignment) {
		try {
			boolean roleUpdated = reqRoleAssignmentsBAO.updateRoleAssignments(updateRoleAssignment);

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
