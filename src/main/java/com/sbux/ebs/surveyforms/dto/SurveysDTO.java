package com.sbux.ebs.surveyforms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="surveys")
public class SurveysDTO {
	
	private List<SurveyDTO> surveys; //The object itself contains the list
	
	@XmlElement(name="survey") //Root Element
	public List<SurveyDTO> getSurveys(){//gets multiple surveys of type SURVEYDTO
		return surveys;
	}
	
	public void setSurveys(List<SurveyDTO> surveys){//sets multiple surveys of type SURVEYDTO
		this.surveys=surveys;
	}
}
