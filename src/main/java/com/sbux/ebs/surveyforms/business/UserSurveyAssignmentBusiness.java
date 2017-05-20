package com.sbux.ebs.surveyforms.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import com.sbux.ebs.surveyforms.dao.SurveyDAO;
import com.sbux.ebs.surveyforms.dao.UserSurveyAssignmentDAO;
import com.sbux.ebs.surveyforms.dto.Error;
import com.sbux.ebs.surveyforms.dto.UserSurveyAssignmentDTO;
import com.sbux.ebs.surveyforms.dto.UserSurveyAssignmentsDTO;
import com.sbux.ebs.surveyforms.entity.SurveyEntity;
import com.sbux.ebs.surveyforms.entity.UserSurveyAssignmentEntity;
import com.sbux.ebs.surveyforms.exception.OperationException;
import com.sbux.ebs.surveyforms.exception.QueryNoResultsException;

@Singleton
public class UserSurveyAssignmentBusiness {
	@EJB
	UserSurveyAssignmentDAO userSurveyAssignmentDao;
	@EJB
	SurveyDAO surveyDao;
	
	public UserSurveyAssignmentsDTO getFilteredUserSurveyAssignment(String userId,String surveyId, String completionDate){
		List<UserSurveyAssignmentEntity> userSurveyAssignments = new ArrayList<UserSurveyAssignmentEntity>();
		
		userSurveyAssignments=userSurveyAssignmentDao.getSurveyAssignmentWithQuery(userId, surveyId, completionDate);
		
		if (userSurveyAssignments.isEmpty()){
			throw new QueryNoResultsException("9001002");
		}
		
//		Will hold list of assignments that would be then sent to usersurvetassignmentSDTO internal variable
		List<UserSurveyAssignmentDTO> userSurveyAssignmentList = new ArrayList<UserSurveyAssignmentDTO>();
		for (UserSurveyAssignmentEntity traverser:userSurveyAssignments)
			userSurveyAssignmentList.add(injectEntityToDTO(traverser));
		
		UserSurveyAssignmentsDTO userSurveyAssignmentsObject = new UserSurveyAssignmentsDTO() ;
		userSurveyAssignmentsObject.setSurveyassignments(userSurveyAssignmentList);
		
		return userSurveyAssignmentsObject;
	}
	
	
	public UserSurveyAssignmentDTO injectEntityToDTO(UserSurveyAssignmentEntity usae){
		UserSurveyAssignmentDTO userSurveyAssignment = new UserSurveyAssignmentDTO();
		userSurveyAssignment.setAssignmentdate(nulltoEmpty(dateToCustomString(usae.getAssignmentDate())));
		userSurveyAssignment.setCompletiondate(nulltoEmpty(dateToCustomString(usae.getCompletionDate())));
		userSurveyAssignment.setSurveyassignmentid(nulltoEmpty(usae.getSurveyAssignmentId()+""));
		userSurveyAssignment.setUserid(nulltoEmpty(usae.getUserId()+""));
		userSurveyAssignment.setSurveyid(nulltoEmpty(usae.getSurvey().getSurveyId()+""));
		return userSurveyAssignment;
	}
//	Null value replaced by ""
	public String nulltoEmpty(String value){
		if (value.equals("null")){
			return "";
		}
		return value;
	}
	public String dateToCustomString(Date inputDate){//Thu Dec 01 11:24:12 PST 2016 to 12/1/2016
		if (inputDate==null){
			return "null";
		}
		SimpleDateFormat formatter=new SimpleDateFormat("M/d/yyyy");
		return formatter.format(inputDate);
		
	}
	
	public void createUserAssignments (UserSurveyAssignmentDTO usdto) {
		// Need to validate usdto.getUserid()		
		SurveyEntity s = findSurvey(usdto.getSurveyid());
		if (s == null)
			throw new OperationException("9001002");
		else
		 userSurveyAssignmentDao.createUserAssignments(createUserSurveyAssignment(usdto));
   }
	private SurveyEntity findSurvey(String sid) {

		try {
			return surveyDao.getSurveyById(sid);
		} catch (Exception q) {
			return null;
		}
	}
	private UserSurveyAssignmentEntity createUserSurveyAssignment(UserSurveyAssignmentDTO usdto)  {
		
		UserSurveyAssignmentEntity u = new UserSurveyAssignmentEntity();
	//	SurveyEntity s = new SurveyEntity();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
       
		u.setUserId(Integer.parseInt(usdto.getUserid()));			
		try {
			u.setAssignmentDate(df.parse(usdto.getAssignmentdate()));
		} catch (java.text.ParseException e1) {
			throw new OperationException("9001004");
		}	
		
		try {
			u.setCompletionDate(df.parse(usdto.getCompletiondate()));
		} catch (java.text.ParseException e1) {
			throw new OperationException("9001005");
		}	
		
	//	s.setSurveyId(Integer.parseInt(usdto.getSurveyid()));
		u.setSurvey(findSurvey(usdto.getSurveyid()));
		return u;		
	}
	
	public Error getErrorMessage(Throwable t) {
		String errorCode = t.getMessage();
		System.out.println("Error Code: " + errorCode);
		Error error = new Error();
		error.setCode(errorCode);
		error.setMessage("Unknown Error");
		if ("9001001".equals(errorCode))
			error.setMessage("No item matches the search criteria provided.");
		else if ("9001002".equals(errorCode))
			error.setMessage("No Survey found.");
		else if ("9001003".equals(errorCode))
			error.setMessage("No User found.");
		else if ("9001004".equals(errorCode))
			error.setMessage("Invalid Assignment Date format.");
		else if ("9001005".equals(errorCode))
			error.setMessage("Invalid Completion Date format.");
		else
			error.setMessage("Unknown Error: Contact System Administrator.");
		return error;
	}
}
