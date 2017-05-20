package com.sbux.ebs.surveyforms.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="answer")
public class AnswerDTO {
	private String answerId;
	//private String surveyId;
	private String userId;
	private String questionId;
	private String optionChoiceId;
	private String answerNumeric;
	private String answerText;
	private String answerYn;
	
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
/*	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
*/	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getOptionChoiceId() {
		return optionChoiceId;
	}
	public void setOptionChoiceId(String optionChoiceId) {
		this.optionChoiceId = optionChoiceId;
	}
	public String getAnswerNumeric() {
		return answerNumeric;
	}
	public void setAnswerNumeric(String answerNumeric) {
		this.answerNumeric = answerNumeric;
	}
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public String getAnswerYn() {
		return answerYn;
	}
	public void setAnswerYn(String answerYn) {
		this.answerYn = answerYn;
	}

}
