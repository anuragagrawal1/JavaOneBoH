package com.sbux.ebs.surveyforms.business;

import com.sbux.ebs.surveyforms.dao.AnswerDAO;
import com.sbux.ebs.surveyforms.dao.SurveyDAO;
import com.sbux.ebs.surveyforms.dto.AnswerDTO;
import com.sbux.ebs.surveyforms.dto.AnswersDTO;
import com.sbux.ebs.surveyforms.dto.Error;
import com.sbux.ebs.surveyforms.entity.AnswerEntity;
import com.sbux.ebs.surveyforms.entity.SurveyEntity;
import com.sbux.ebs.surveyforms.exception.OperationException;
import com.sbux.ebs.surveyforms.exception.QueryNoResultsException;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class AnswerBusiness {
	@EJB
	AnswerDAO answerDao;
	@EJB
	SurveyDAO surveyDao;

	public void createAnswer(String surveyId, AnswerDTO ans) {
		System.out.println("Validating Survey ID..." + ans.getAnswerText());
		SurveyEntity s = findSurvey(surveyId);
		if (s == null)
			throw new OperationException("9001002");
		else
			answerDao.createAnswer(createAns(surveyId, ans));
	}

	private SurveyEntity findSurvey(String sid) {

		try {
			return surveyDao.getSurveyById(sid);
		} catch (Exception q) {
			return null;
		}
	}

	private AnswerEntity createAns(String surveyId, AnswerDTO ans) {
		
		AnswerEntity a = new AnswerEntity();
		SurveyEntity s = new SurveyEntity(); 
		String answyn;

		a.setQuestionId(Integer.parseInt(ans.getQuestionId()));
		a.setOptionChoiceId(Integer.parseInt(ans.getOptionChoiceId()));
		a.setUserId(Integer.parseInt(ans.getUserId()));
		if (!ans.getAnswerNumeric().isEmpty()) 
	    	a.setAnswerNumeric(Integer.parseInt(ans.getAnswerNumeric()));
		a.setAnswerText(ans.getAnswerText());
		
		answyn = ans.getAnswerYn();
        switch(answyn){
		case "Y":	answyn="1";
			break;
		case "N": answyn="0";
			break;
		}	
                
	    // a.setAnswerYn(Byte.valueOf(ans.getAnswerYn()));
		 a.setAnswerYn(Byte.valueOf(answyn));
		// s.setSurveyId(Integer.parseInt(ans.getSurveyId()));
//		s.setSurveyId(Integer.parseInt(surveyId));
//		a.setSurvey(s);
		a.setSurvey(findSurvey(surveyId));
		
		return a;
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
		else
			error.setMessage("Unknown Error: Contact System Administrator.");
		return error;
	}

	public AnswersDTO getAnswers(String surveyId) {
		List<AnswerEntity> answerEntList;
		AnswersDTO returnAnswers=new AnswersDTO();
		List<AnswerDTO> answerDtoList = new ArrayList<AnswerDTO>();
		answerEntList=answerDao.getAnswersBySurveyId(surveyId);
		if(answerEntList.isEmpty()){
			throw new QueryNoResultsException("9001002");
		}
		for (AnswerEntity traverser:answerEntList)
			answerDtoList.add(convertAnswerEntToAnswerDTO(traverser));
		returnAnswers.setAnswers(answerDtoList);
		return returnAnswers;
	}
	
	public AnswerDTO convertAnswerEntToAnswerDTO(AnswerEntity ae) {
		AnswerDTO answer = new AnswerDTO();
		answer.setAnswerId(ae.getAnswerId()+"");
		answer.setAnswerText(nulltoEmpty(ae.getAnswerText()));
		answer.setAnswerNumeric(nulltoEmpty(ae.getAnswerNumeric()+""));
		switch (nulltoEmpty(ae.getAnswerYn()+"")){
		case "1":answer.setAnswerYn("Y");
		break;
		case "0":answer.setAnswerYn("N");
		break;
		case "":answer.setAnswerYn("");
		break;
		}
		answer.setOptionChoiceId(nulltoEmpty(ae.getOptionChoiceId()+""));
		answer.setQuestionId(nulltoEmpty(ae.getQuestionId()+""));
		answer.setUserId(nulltoEmpty(ae.getUserId()+""));
		return answer;
	}
	
//	Null value replaced by ""
	public String nulltoEmpty(String value){
		if (value.equals("null")){
			System.out.println("\nNull Value detected");
			return "";
		}
		return value;
	}
	
}
