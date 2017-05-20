package com.sbux.ebs.surveyforms.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "question")
public class QuestionDTO {

	private int questionId;
	private String enable_Flag;
	private String questionText;
	private String questionRequiredYN;
	private String allowMultipleOptionAnswersYN;
	private String inputTypeText;
	private List<OptionChoiceDTO> optionChoices;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	@XmlElement(name = "enabledFlag")
	public String getEnable_Flag() {
		return enable_Flag;
	}

	public void setEnable_Flag(String enable_Flag) {
		this.enable_Flag = enable_Flag;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionRequiredYN() {
		return questionRequiredYN;
	}

	public void setQuestionRequiredYN(String questionRequiredYN) {
		this.questionRequiredYN = questionRequiredYN;
	}

	public String getAllowMultipleOptionAnswersYN() {
		return allowMultipleOptionAnswersYN;
	}

	public void setAllowMultipleOptionAnswersYN(String allowMultipleOptionAnswersYN) {
		this.allowMultipleOptionAnswersYN = allowMultipleOptionAnswersYN;
	}

	public String getInputTypeText() {
		return inputTypeText;
	}

	public void setInputTypeText(String inputTypeText) {
		this.inputTypeText = inputTypeText;
	}

	@XmlElement(name = "optionChoice")
	@XmlElementWrapper(name="optionChoices")
    public List<OptionChoiceDTO> getOptionChoices() {
		return optionChoices;
	}

	public void setOptionChoices(List<OptionChoiceDTO> optionChoices) {
		this.optionChoices = optionChoices;
	}

}
