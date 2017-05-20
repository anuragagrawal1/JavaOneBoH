package com.sbux.ebs.surveyforms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="questions")
public class Questions {
	
	/*List<Question> questions;

	@XmlElement(name="question")
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}*/
	
	List<QuestionDTO> completeQuestions;

	@XmlElement(name="question")
	public List<QuestionDTO> getCompleteQuestions() {
		return completeQuestions;
	}

	public void setCompleteQuestions(List<QuestionDTO> completeQuestions) {
		this.completeQuestions = completeQuestions;
	}
}
