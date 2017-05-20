package com.sbux.ebs.surveyforms.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the answers database table.
 * 
 */
@Entity
@Table(name="answers")
@NamedQueries({
@NamedQuery(name="AnswerEntity.findAll", query="SELECT a FROM AnswerEntity a"),
@NamedQuery(name="AnswerEntity.findBySurveyId", query="SELECT a FROM AnswerEntity a WHERE a.survey.surveyId = :surveyId")
})

public class AnswerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="answer_id")
	private int answerId;

	@Column(name="answer_numeric")
	private int answerNumeric;

	@Column(name="answer_text")
	private String answerText;

	@Column(name="answer_yn")
	private byte answerYn;

	@Column(name="option_choice_id")
	private int optionChoiceId;

	@Column(name="question_id")
	private int questionId;

	@Column(name="user_id")
	private int userId;
	
	
	//bi-directional many-to-one association to SurveyEntity
	@ManyToOne
	@JoinColumn(name="survey_id")
	private SurveyEntity survey;

	public AnswerEntity() {
	}

	public int getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public int getAnswerNumeric() {
		return this.answerNumeric;
	}

	public void setAnswerNumeric(int answerNumeric) {
		this.answerNumeric = answerNumeric;
	}

	public String getAnswerText() {
		return this.answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public byte getAnswerYn() {
		return this.answerYn;
	}

	public void setAnswerYn(byte answerYn) {
		this.answerYn = answerYn;
	}

	public int getOptionChoiceId() {
		return this.optionChoiceId;
	}

	public void setOptionChoiceId(int optionChoiceId) {
		this.optionChoiceId = optionChoiceId;
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public SurveyEntity getSurvey() {
		return this.survey;
	}

	public void setSurvey(SurveyEntity survey) {
		this.survey = survey;
	}
}