package com.sbux.ebs.surveyforms.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="surveyassignment")
public class UserSurveyAssignmentDTO {
	private String surveyassignmentid;
	private String userid;
	private String assignmentdate;
	private String completiondate;
	private String surveyid;

	@XmlElement(name="surveyid")
	public String getSurveyid() {
		return surveyid;
	}
	public void setSurveyid(String surveyid) {
		this.surveyid = surveyid;
	}
	@XmlElement(name="surveyassignmentid")
	public String getSurveyassignmentid() {
		return surveyassignmentid;
	}
	public void setSurveyassignmentid(String surveyassignmentid) {
		this.surveyassignmentid = surveyassignmentid;
	}
	@XmlElement(name="userid")
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@XmlElement(name="assignmentdate")
	public String getAssignmentdate() {
		return assignmentdate;
	}
	public void setAssignmentdate(String assignmentdate) {
		this.assignmentdate = assignmentdate;
	}
	@XmlElement(name="completiondate")
	public String getCompletiondate() {
		return completiondate;
	}
	public void setCompletiondate(String completiondate) {
		this.completiondate = completiondate;
	}
}
