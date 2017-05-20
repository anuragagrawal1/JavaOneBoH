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

import com.sbux.ebs.surveyforms.business.QuestionBusiness;
import com.sbux.ebs.surveyforms.dto.QuestionDTO;
import com.sbux.ebs.surveyforms.dto.Questions;
import com.sbux.ebs.surveyforms.exception.ExceptionManager;
import com.sbux.ebs.surveyforms.exception.OperationException;

@Singleton
@Path("/surveys/{surveyId}/questions")
@SuppressWarnings("javadoc")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public class SurveyQuestionService {
	@EJB
	QuestionBusiness questionBusiness;
	
	@GET
	public Response getAllQuestionsBySurveyId(@PathParam("surveyId") int id,@QueryParam("option") String option) {
		try {
			if (option == null) {
				option = "all";
			}
			switch (option) {
			case "all":
				return buildOkResponse(questionBusiness.getAllQuestions(id, "all"));
			case "enabled":
				return buildOkResponse(questionBusiness.getAllQuestionsByEnabledFlag(id, "enabled", "1"));
			case "disabled":
				return buildOkResponse(questionBusiness.getAllQuestionsByEnabledFlag(id, "disabled", "0"));
			default:
				throw new OperationException("9001001");
			}
		} catch (Throwable t) {
			return buildErrorResponse(t);
		}
	}

	@GET
	@Path("{questionId}")
	public Response getQuestionsById(@PathParam("surveyId") int surveyId,@PathParam("questionId") int questionId) {
		try {
			return buildOkResponse(questionBusiness.getQuestionDTO(surveyId, questionId));
		} catch (Throwable t) {
			return buildErrorResponse(t);
		}
	}

	@GET
	@Path("required")
	public Response getRequiredQuestionsById(@PathParam("surveyId") int surveyId) {
		try {
			return buildOkResponse(questionBusiness.getRequiredQuestions(surveyId));
		} catch (Throwable t) {
			return buildErrorResponse(t);
		}
	}

	@POST
	public Response createQuestion(@PathParam("surveyId") int surveyId,Questions questions) {
		System.out.println("In Create Question");
		try {
			for (QuestionDTO ques : questions.getCompleteQuestions())
				questionBusiness.createQuestion(surveyId, ques);
		} catch (Throwable t) {
			return buildErrorResponse(t);
		}
		return Response.status(Response.Status.ACCEPTED).build();
	}

	@PUT
	@Path("{questionId}")
	public Response disableQuestion(@PathParam("surveyId") int surveyId, @PathParam("questionId") int questionId,
			@QueryParam("option") String option) {
		try {
			switch (option) {
			case "enable":
				questionBusiness.updateQuestionStatus(surveyId, questionId, "1");
				return Response.status(Response.Status.ACCEPTED).build();
			case "disable":
				questionBusiness.updateQuestionStatus(surveyId, questionId, "0");
				return Response.status(Response.Status.ACCEPTED).build();
			default:
				throw new OperationException("9001001");		
			}
		} catch (Throwable t) {
			return buildErrorResponse(t);
		}
	}
	
	private Response buildOkResponse(Object objResponse) {
		return Response.status(Response.Status.OK).entity(objResponse).build();
	}

	private Response buildErrorResponse(Throwable exception) {
		ExceptionManager em = new ExceptionManager();
		exception.printStackTrace();
		return Response.status(Response.Status.BAD_REQUEST).entity(em.getErrorMessage(exception)).build();
	}
}
