package com.sbux.ebs.surveyforms.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the option_choices database table.
 * 
 */
@Entity
@Table(name="option_choices")
@NamedQuery(name="OptionChoiceEntity.findAll", query="SELECT o FROM OptionChoiceEntity o")
public class OptionChoiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="option_choice_id")
	private int optionChoiceId;

	@Column(name="option_choice_name")
	private String optionChoiceName;

	public OptionChoiceEntity() {
	}

	public int getOptionChoiceId() {
		return this.optionChoiceId;
	}

	public void setOptionChoiceId(int optionChoiceId) {
		this.optionChoiceId = optionChoiceId;
	}

	public String getOptionChoiceName() {
		return this.optionChoiceName;
	}

	public void setOptionChoiceName(String optionChoiceName) {
		this.optionChoiceName = optionChoiceName;
	}

}