package com.sbux.ebs.surveyforms.rest;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sbux.ebs.surveyforms.business.SurveyBusiness;
import com.sbux.ebs.surveyforms.dto.SurveysDTO;
import com.sbux.ebs.surveyforms.exception.ExceptionManager;
import com.sbux.ebs.surveyforms.exception.OperationException;
import com.sbux.ebs.surveyforms.exception.QueryNoResultsException;

@Singleton
@Path("/surveys")
@SuppressWarnings("javadoc")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class SurveyService {
	@EJB
	SurveyBusiness surveyBusiness;
	@GET
	@Path("{surveyId}")
 	public Response getSurveybyId(@PathParam("surveyId")String surveyId){
//		return buildOKResponse("Service /surveys/{surveyId} called with Id " + surveyId);
//		System.out.println("*DEBUG TEAM 2* : "+ surveyId);
		try{
			return buildOKResponse(surveyBusiness.getSurvey(surveyId));
		}catch (Throwable t){
			return buildErrorResponse(t);
		}
		
	}
	
	private Response buildErrorResponse(Throwable exception) {
		ExceptionManager em = new ExceptionManager();
		exception.printStackTrace();
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(em.getErrorMessage(exception))
                       .build();
    }

	@GET
	public Response getFilteredSurveys(@QueryParam("option")String option){
		try {
			if (option==null){
				option="all";
			}
			switch(option){
			case "all":
				System.out.println("All");
				return buildOKResponse(surveyBusiness.getFilteredSurveys("all"));
			case "active":
				System.out.println("Active");
				return buildOKResponse(surveyBusiness.getFilteredSurveys("active"));
			case "inactive":
				System.out.print("Inactive");
				return buildOKResponse(surveyBusiness.getFilteredSurveys("inactive"));
			default:
				throw new OperationException("9001004");
			}
		}catch (Throwable exception){
			return buildErrorResponse(exception);
		}
	}
	
	@PUT
	@Path("{surveyId}")
//	public Response updateSurvey(@PathParam("surveyId")String surveyId){
//		return Response.status(Response.Status.ACCEPTED).entity("Called : "+surveyId).build();
//		
//	}
	public Response updateSurvey(@PathParam("surveyId")String surveyId,SurveysDTO surveys){
	try{
//		return Response.status(Response.Status.ACCEPTED).entity("Called : "+surveyId).build();
		
		if(surveyBusiness.updateSurveyById(surveyId, surveys)==true){
			return Response.status(Response.Status.ACCEPTED).entity("").build();
		}else{
			throw new QueryNoResultsException("9001002");
//			return Response.status(Response.Status.BAD_REQUEST).entity("Record not found.").build();	
		}
	}catch(Throwable exception){
		return buildErrorResponse(exception);
	}
	}
	
	@POST
	public Response createMultipleSurveys(){
		return Response.status(Response.Status.ACCEPTED).entity("Dummy Create Mutiple Surveys called; to be implemented").build();
	}
	
	
	private Response buildOKResponse(Object returnObject) {
//		System.out.println("In build on Response");
		return Response.status(Response.Status.OK).entity(returnObject).build();
	}
}