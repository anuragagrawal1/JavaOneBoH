package com.sbux.ebs.surveyforms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="survey")
public class SurveyDTO {
	private String surveyId;
	private String surveyName;
	private String instructions;
	private String otherHeaderInfo;
	private String effectiveStartDate;
	private String effectiveEndDate;
	private String status;
//	private List<QuestionsDTO> questions;
	
	@XmlElement(name="surveyid")
	public String getSurveyId(){
		return surveyId;
	}
	public void setSurveyId(String surveyId){
		this.surveyId=surveyId;
	}
	
	@XmlElement(name="surveyname")
	public String getSurveyName(){
		return surveyName;
	}
	
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	
	@XmlElement(name="instructions")	
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	@XmlElement(name="otherheaderinfo")
	public String getOtherHeaderInfo() {
		return otherHeaderInfo;
	}
	public void setOtherHeaderInfo(String otherHeaderInfo) {
		this.otherHeaderInfo = otherHeaderInfo;
	}
	
	@XmlElement(name="effectivestartdate")
	public String getEffectiveStartDate() {
		return effectiveStartDate;
	}
	public void setEffectiveStartDate(String effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}
	
	@XmlElement(name="effectiveenddate")
	public String getEffectiveEndDate() {
		return effectiveEndDate;
	}
	public void setEffectiveEndDate(String effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}
	
	@XmlElement(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	//	@XmlElement(name="questions")//Root element ; not coded as may be from another service
	

}
