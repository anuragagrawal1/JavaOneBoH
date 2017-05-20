package com.sbux.ebs.surveyforms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "answers")
public class AnswersDTO {

	private List<AnswerDTO> answers;

	@XmlElement(name = "answer")
	public List<AnswerDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerDTO> answers) { 														
		this.answers = answers;
	}

}
