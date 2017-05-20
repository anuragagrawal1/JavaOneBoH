package com.sbux.ebs.surveyforms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the input_types database table.
 * 
 */
@Entity
@Table(name = "input_types")
@NamedQueries({ @NamedQuery(name = "InputTypeEntity.findAll", query = "SELECT i FROM InputTypeEntity i"),
		@NamedQuery(name = "InputTypeEntity.findInputText", query = "SELECT i FROM InputTypeEntity i where i.inputTypeId=:inputTypeId"),
		@NamedQuery(name = "InputTypeEntity.findInputTypeId", query = "SELECT i FROM InputTypeEntity i where i.inputTypeName=:inputTypeName") })
public class InputTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "input_type_id")
	private int inputTypeId;

	@Column(name = "input_type_name")
	private String inputTypeName;

	// bi-directional many-to-one association to SurveyQuestionEntity
	@OneToMany(mappedBy = "inputType")
	private List<SurveyQuestionEntity> surveyQuestions;

	public InputTypeEntity() {
	}

	public int getInputTypeId() {
		return this.inputTypeId;
	}

	public void setInputTypeId(int inputTypeId) {
		this.inputTypeId = inputTypeId;
	}

	public String getInputTypeName() {
		return this.inputTypeName;
	}

	public void setInputTypeName(String inputTypeName) {
		this.inputTypeName = inputTypeName;
	}

	public List<SurveyQuestionEntity> getSurveyQuestions() {
		return this.surveyQuestions;
	}

	public void setSurveyQuestions(List<SurveyQuestionEntity> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}

	public SurveyQuestionEntity addSurveyQuestion(SurveyQuestionEntity surveyQuestion) {
		getSurveyQuestions().add(surveyQuestion);
		surveyQuestion.setInputType(this);

		return surveyQuestion;
	}

	public SurveyQuestionEntity removeSurveyQuestion(SurveyQuestionEntity surveyQuestion) {
		getSurveyQuestions().remove(surveyQuestion);
		surveyQuestion.setInputType(null);

		return surveyQuestion;
	}

}
