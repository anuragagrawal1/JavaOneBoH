package com.sbux.ebs.surveyforms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="surveyassignments")
public class UserSurveyAssignmentsDTO {
	private List<UserSurveyAssignmentDTO> surveyassignments;

	@XmlElement(name="surveyassignment")
	public List<UserSurveyAssignmentDTO> getSurveyassignments() {
		return surveyassignments;
	}

	public void setSurveyassignments(List<UserSurveyAssignmentDTO> surveyassignments) {
		this.surveyassignments = surveyassignments;
	}
}
