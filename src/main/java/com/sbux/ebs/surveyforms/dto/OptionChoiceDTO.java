package com.sbux.ebs.surveyforms.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OptionChoice")
public class OptionChoiceDTO {
	private int optionChoiceId;
	private String optionChoiceName;

	// no Argument Constructor
	public OptionChoiceDTO() {
		super();
	}

	public int getOptionChoiceId() {
		return optionChoiceId;
	}

	public void setOptionChoiceId(int optionChoiceId) {
		this.optionChoiceId = optionChoiceId;
	}

	public String getOptionChoiceName() {
		return optionChoiceName;
	}

	public void setOptionChoiceName(String optionChoiceName) {
		this.optionChoiceName = optionChoiceName;
	}

}
