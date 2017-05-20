package com.sbux.ebs.surveyforms.business;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import com.sbux.ebs.surveyforms.dao.SurveyDAO;
import com.sbux.ebs.surveyforms.dto.SurveyDTO;
import com.sbux.ebs.surveyforms.dto.SurveysDTO;
import com.sbux.ebs.surveyforms.entity.SurveyEntity;
import com.sbux.ebs.surveyforms.exception.OperationException;
import com.sbux.ebs.surveyforms.exception.QueryNoResultsException;

@Singleton
public class SurveyBusiness {
	@EJB
	SurveyDAO surveyDao;
	
	public SurveyDTO getSurvey(String surveyId){
		System.out.println("surveyDTO.getSurvey : surveyId : " + surveyId);
		SurveyEntity se=surveyDao.getSurveyById(surveyId);
		if (se==null){
			throw new QueryNoResultsException("9001002");
		}
		return(convertSurveyEntToSurveyDTO(se));
	}

//	Common code to move a dao to DTO
//	Null values would be replaced by ""
	public SurveyDTO convertSurveyEntToSurveyDTO(SurveyEntity se) {
		SurveyDTO survey = new SurveyDTO();
		survey.setSurveyId(se.getSurveyId()+"");
		survey.setSurveyName(nulltoEmpty(se.getSurveyName()+""));
		survey.setEffectiveEndDate(nulltoEmpty(dateToCustomString(se.getEffectiveEndDate())));
		survey.setEffectiveStartDate(nulltoEmpty(dateToCustomString(se.getEffectiveStartDate())));
		survey.setInstructions(nulltoEmpty(se.getInstructions()+""));
		survey.setOtherHeaderInfo(nulltoEmpty(se.getOtherHeaderInfo()+""));
		survey.setStatus(nulltoEmpty(se.getStatus()+""));
//		System.out.println("dao to DTO : " + survey.getSurveyName());
		
		return survey;
	}
	
	public String dateToCustomString(Date inputDate){//Thu Dec 01 11:24:12 PST 2016 to 12/1/2016
		if (inputDate==null){
			return "null";
		}
		SimpleDateFormat formatter=new SimpleDateFormat("M/d/yyyy");
//		System.out.println("dateToCustomString : "+inputDate.getTime());
//		System.out.println("dateToCustomString : "+formatter.format(inputDate));
		return formatter.format(inputDate);
		
	}
//	Null value replaced by ""
	public String nulltoEmpty(String value){
//		System.out.print("\n-2-RK DEBUG Input Value : "+ value);
//		if (value.isEmpty()){
//			System.out.println("\nValue is empty");
//		}
//		if (value.equals(null)){
//			System.out.println("\nNull Object detected");
//		}
		if (value.equals("null")){
			System.out.println("\nNull Value detected");
			return "";
		}
		return value;
//		System.out.print("\nRK DEBUG Output ternary " + ((value.trim() == "null") ? "true" : "false"));
//		return ((value == "null") ? "" : value);
	}
	
	public SurveysDTO getFilteredSurveys(String option){
		List<SurveyEntity> se= new ArrayList<SurveyEntity>();
		switch(option){
		case "all":	se=surveyDao.getAllSurveys();
			break;
		case "active": se=surveyDao.getAllActiveSurveys();
			break;
		case "inactive": se=surveyDao.getAllInactiveSurveys();
			break;
		}
//		Time to transfer List<entity> to List<dto> using set from SurveysDTO
		if (se.isEmpty()){
			throw new QueryNoResultsException("9001002");
		}
		SurveysDTO surveys= new SurveysDTO(); // Object that already has a var and method to manage List. Hence no List used
		List<SurveyDTO> surveyList = new ArrayList<SurveyDTO>();
		for(SurveyEntity traverser:se)
			surveyList.add(convertSurveyEntToSurveyDTO(traverser));
		surveys.setSurveys(surveyList);
		return surveys;
	}
	
	public SurveyEntity findSurvey(String surveyId){
		SurveyEntity se;
		try{
			se =surveyDao.getSurveyById(surveyId);
			return se;
		}catch (Exception e){
			return null;
		}
	}
	
	public boolean updateSurveyById(String surveyId,SurveysDTO surveys){
//		SurveyDTO singleSurvey;
		SurveyEntity survey;
		for(SurveyDTO se:surveys.getSurveys()){
			survey=findSurvey(surveyId);
			if (survey == null){
				return false;
			}
			surveyDao.updateSurveyById(survey); //May be redundant
			surveyDao.updateSurveyById(dtoToEntity(survey, se));
		}
		return true;
	}
	
	public SurveyEntity dtoToEntity(SurveyEntity se,SurveyDTO surveyDto){//Uses Anti-Pattern where Persist is used to update the record after attaching to it (by querying)
		se.setSurveyName(surveyDto.getSurveyName());
		se.setInstructions(surveyDto.getInstructions());
		se.setStatus(surveyDto.getStatus());
		se.setOtherHeaderInfo(surveyDto.getOtherHeaderInfo());
		se.setEffectiveEndDate(stringDateToSqlDate(surveyDto.getEffectiveEndDate()));
		se.setEffectiveStartDate(stringDateToSqlDate(surveyDto.getEffectiveStartDate()));
		return se;// May be redundant as object may e passed by reference.
	}
	
	public Timestamp stringDateToSqlDate(String stringDate){
//		SimpleDateFormat dateParser=new SimpleDateFormat("EEE MMM dd hh:mm:ss a z yyyy");//Fri Apr 14 GMT 2017; Sensitive to day to date correctness too
		SimpleDateFormat dateParser=new SimpleDateFormat("M/d/yyyy");//Fri Apr 14 GMT 2017
		System.out.println("\n stringDateToSqlDate : incoming date : "+stringDate);
		dateParser.setLenient(false);
		
		Date uDate;
		java.sql.Timestamp sqlDate;
		try{
//			System.out.println(new SimpleDateFormat("M/d/yyyy").parse("4/18/2017"));
//			System.out.println(new SimpleDateFormat("M/d/yyyy z").parse("4/18/2017 PST"));
//			System.out.println(dateParser.parse("Fri Apr 14 07:16:25 PM GMT 2017"));
			System.out.println(dateParser.parse(stringDate));
			uDate=dateParser.parse(stringDate);
		}catch (ParseException p1){
			System.out.println("Date Parsing exception thrown" + p1.getMessage());
//			throw new NoResultException("Invalid Date Format.");
			throw new OperationException("9001005");
		}
		sqlDate=new java.sql.Timestamp(uDate.getTime());
//		System.out.println("\n stringDateToSqlDate : Java.util.date : "+uDate.toString() + " : " +uDate.getTime());
//		System.out.println("\n stringDateToSqlDate : Java.sql.date : "+sqlDate.toString());
		return(sqlDate);
	}
}


