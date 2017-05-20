package com.sbux.ebs.surveyforms.rest;
import java.util.Arrays;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sbux.ebs.surveyforms.business.AnswerBusiness; 
import com.sbux.ebs.surveyforms.dto.AnswerDTO;
import com.sbux.ebs.surveyforms.dto.AnswersDTO;

@Singleton
@Path("/surveyanswers")
@SuppressWarnings("javadoc")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class AnswerService {
	@EJB
	AnswerBusiness answerBusiness;
	     
    @POST
    @Path("/{surveyId}/answers")  
  
    public Response createAnswers(@PathParam("surveyId")String surveyId, AnswersDTO answers) {    
    	System.out.println("In createAnswers, Survey ID: " + surveyId);
        try {
            for(AnswerDTO ans: answers.getAnswers())
                answerBusiness.createAnswer(surveyId, ans);
        } catch(Throwable t) {
            return buildErrorResponse(t);
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
    @GET    
    @Path("/{surveyId}/answers")  
	public Response getAnswerBySurveyId(@PathParam("surveyId")String surveyId){
    	try{
    		return buildOKResponse(answerBusiness.getAnswers(surveyId));
    	}catch (Throwable exception){
    		return buildErrorResponse(exception);
    	}

    	
	}
	
	public Response buildOKResponse(Object obj){
		return Response.status(Response.Status.OK).entity(obj).build();
	}    

	private Response buildErrorResponse(Throwable t) {
        t.printStackTrace();
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(answerBusiness.getErrorMessage(t))
                       .build();
    }
 
}
