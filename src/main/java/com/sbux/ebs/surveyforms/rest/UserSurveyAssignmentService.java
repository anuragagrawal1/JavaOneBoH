package com.sbux.ebs.surveyforms.rest;


import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sbux.ebs.surveyforms.business.UserSurveyAssignmentBusiness;
import com.sbux.ebs.surveyforms.dto.UserSurveyAssignmentDTO;
import com.sbux.ebs.surveyforms.dto.UserSurveyAssignmentsDTO;
import com.sbux.ebs.surveyforms.entity.UserSurveyAssignmentEntity;
import com.sbux.ebs.surveyforms.exception.ExceptionManager;

@Singleton
@Path("/administration/surveyassignments")
@SuppressWarnings("javadoc")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class UserSurveyAssignmentService {
	@EJB
	UserSurveyAssignmentBusiness userSurveyAssignment;
	//	/surveyassignments?userId={userId}&assignmentid={assignmentId}&surveyId={surveyId}&completiondate={completiondate}
	@GET
	public Response getFilteredSurveyAssignments(@QueryParam("userId")String userId, 
												 @QueryParam("surveyId")String surveyId,
												 @QueryParam("completionDate")String completionDate){
		try{
			if (userId==null)
				userId="";
			if (surveyId==null)
				surveyId="";
			if (completionDate==null)
				completionDate="";
			return buildOKResponse(userSurveyAssignment.getFilteredUserSurveyAssignment(userId, surveyId, completionDate));
		}catch (Throwable exception){
			return buildErrorResponse_em(exception);
		}

//		return  buildOKResponse("Fetch Survey Assignments with Query Param "+userId+surveyId+completionDate);
	}
	
	private Response buildErrorResponse_em(Throwable exception) {
		ExceptionManager em = new ExceptionManager();
		exception.printStackTrace();
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(em.getErrorMessage(exception))
                       .build();
    }
	
	public Response buildOKResponse(Object obj){
		return Response.status(Status.OK).entity(obj).build();
	}
	 @POST
	    public Response createUserAssignments(UserSurveyAssignmentsDTO usdtos) {    
	    	System.out.println("In createUserAssignments... ");
	        try {
	            for(UserSurveyAssignmentDTO usdto: usdtos.getSurveyassignments()) {
	            	System.out.println("Before userSurveyAssignment... ");
	            	userSurveyAssignment.createUserAssignments(usdto);
	            }
	        } catch(Throwable t) {
	            return buildErrorResponse(t); 
	        }
	        return Response.status(Response.Status.ACCEPTED).build();
	    } 

		private Response buildErrorResponse(Throwable t) {
	        t.printStackTrace();
	        return Response.status(Response.Status.BAD_REQUEST)
	                       .entity(userSurveyAssignment.getErrorMessage(t))
	                       .build();
	    }

}
