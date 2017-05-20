package com.sbux.ebs.surveyforms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the survey_questions database table.
 * 
 */
@Entity
@Table(name = "survey_questions")
@NamedQueries({ @NamedQuery(name = "SurveyQuestionEntity.findAll", query = "SELECT s FROM SurveyQuestionEntity s"),
		@NamedQuery(name = "SurveyQuestionEntity.findBySurveyId", query = "SELECT s FROM SurveyQuestionEntity s WHERE s.surveyId =:surveyId"),
		@NamedQuery(name = "SurveyQuestionEntity.findBySurveyIdByEnabledFlag", query = "SELECT s FROM SurveyQuestionEntity s WHERE s.surveyId =:surveyId and s.enabledFlag =:enabledFlag"),
		@NamedQuery(name = "SurveyQuestionEntity.findBySurveyIdAndQuestionId", query = "SELECT s FROM SurveyQuestionEntity s WHERE s.surveyId =:surveyId and s.questionId= :questionId"),		
		@NamedQuery(name = "SurveyQuestionEntity.findBySurveyIdQuesId", query = "SELECT s FROM SurveyQuestionEntity s WHERE s.surveyId = :surveyId and s.questionId= :questionId and s.enabledFlag=:enabledFlag"),
		@NamedQuery(name = "SurveyQuestionEntity.findRequiredQuestionsBySurveyId", query = "SELECT s FROM SurveyQuestionEntity s WHERE s.surveyId = :surveyId and s.enabledFlag=:enabledFlag and s.questionRequiredYn=:questionRequiredYn") })
        
public class SurveyQuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private int questionId;

	@Column(name = "allow_multiple_option_answers_yn")
	private byte allowMultipleOptionAnswersYn;

	@Column(name = "enabled_flag")
	private String enabledFlag;

	@Column(name = "question_required_yn")
	private byte questionRequiredYn;

	@Column(name = "question_text")
	private String questionText;

	@Column(name = "survey_id")
	private int surveyId;

	// bi-directional many-to-one association to OptionChoiceEntity
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="question_id")
	private List<OptionChoiceEntity> optionChoices;

	// bi-directional many-to-one association to InputTypeEntity
	@ManyToOne
	@JoinColumn(name = "input_type_id")
	private InputTypeEntity inputType;

	public SurveyQuestionEntity() {
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public byte getAllowMultipleOptionAnswersYn() {
		return this.allowMultipleOptionAnswersYn;
	}

	public void setAllowMultipleOptionAnswersYn(byte allowMultipleOptionAnswersYn) {
		this.allowMultipleOptionAnswersYn = allowMultipleOptionAnswersYn;
	}

	public String getEnabledFlag() {
		return this.enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public byte getQuestionRequiredYn() {
		return this.questionRequiredYn;
	}

	public void setQuestionRequiredYn(byte questionRequiredYn) {
		this.questionRequiredYn = questionRequiredYn;
	}

	public String getQuestionText() {
		return this.questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public int getSurveyId() {
		return this.surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public List<OptionChoiceEntity> getOptionChoices() {
		return this.optionChoices;
	}

	public void setOptionChoices(List<OptionChoiceEntity> optionChoices) {
		this.optionChoices = optionChoices;
	}

	public OptionChoiceEntity addOptionChoice(OptionChoiceEntity optionChoice) {
		getOptionChoices().add(optionChoice);
	//	optionChoice.setSurveyQuestion(this);

		return optionChoice;
	}

	public OptionChoiceEntity removeOptionChoice(OptionChoiceEntity optionChoice) {
		getOptionChoices().remove(optionChoice);
//		optionChoice.setSurveyQuestion(null);

		return optionChoice;
	}

	public InputTypeEntity getInputType() {
		return this.inputType;
	}

	public void setInputType(InputTypeEntity inputType) {
		this.inputType = inputType;
	}

}