package com.sbux.ebs.surveyforms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the surveys database table.
 * 
 */
@Entity
@Table(name="surveys")
@NamedQueries({
	@NamedQuery(name="SurveyEntity.findAll", query="SELECT s FROM SurveyEntity s"),
	@NamedQuery(name="SurveyEntity.findById", query="SELECT s FROM SurveyEntity s where s.surveyId = :surveyId"),
	@NamedQuery(name="SurveyEntity.findAllActive", query="select s from SurveyEntity s where s.effectiveStartDate <= CURRENT_TIMESTAMP AND s.effectiveEndDate >= CURRENT_TIMESTAMP"),
	@NamedQuery(name="SurveyEntity.findAllInactive", query="select s from SurveyEntity s where (s.effectiveStartDate > CURRENT_TIMESTAMP) OR (s.effectiveEndDate < CURRENT_TIMESTAMP)"),
	@NamedQuery(name="SurveyEntity.findAllSurveys",query="select s from SurveyEntity s")
})
public class SurveyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="survey_id")
	private int surveyId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="effective_end_date")
	private Date effectiveEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="effective_start_date")
	private Date effectiveStartDate;

	private String instructions;

	@Column(name="other_header_info")
	private String otherHeaderInfo;

	private String status;

	@Column(name="survey_name")
	private String surveyName;

	//bi-directional many-to-one association to AnswerEntity
	@OneToMany(mappedBy="survey")
	private List<AnswerEntity> answers;

	//bi-directional many-to-one association to UserSurveyAssignmentEntity
	@OneToMany(mappedBy="survey")
	private List<UserSurveyAssignmentEntity> userSurveyAssignments;

	public SurveyEntity() {
	}

	public int getSurveyId() {
		return this.surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public Date getEffectiveEndDate() {
		return this.effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public Date getEffectiveStartDate() {
		return this.effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public String getInstructions() {
		return this.instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getOtherHeaderInfo() {
		return this.otherHeaderInfo;
	}

	public void setOtherHeaderInfo(String otherHeaderInfo) {
		this.otherHeaderInfo = otherHeaderInfo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSurveyName() {
		return this.surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public List<AnswerEntity> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<AnswerEntity> answers) {
		this.answers = answers;
	}

	public AnswerEntity addAnswer(AnswerEntity answer) {
		getAnswers().add(answer);
		answer.setSurvey(this);

		return answer;
	}

	public AnswerEntity removeAnswer(AnswerEntity answer) {
		getAnswers().remove(answer);
		answer.setSurvey(null);

		return answer;
	}
	public List<UserSurveyAssignmentEntity> getUserSurveyAssignments() {
		return this.userSurveyAssignments;
	}

	public void setUserSurveyAssignments(List<UserSurveyAssignmentEntity> userSurveyAssignments) {
		this.userSurveyAssignments = userSurveyAssignments;
	}

	public UserSurveyAssignmentEntity addUserSurveyAssignment(UserSurveyAssignmentEntity userSurveyAssignment) {
		getUserSurveyAssignments().add(userSurveyAssignment);
		userSurveyAssignment.setSurvey(this);

		return userSurveyAssignment;
	}

	public UserSurveyAssignmentEntity removeUserSurveyAssignment(UserSurveyAssignmentEntity userSurveyAssignment) {
		getUserSurveyAssignments().remove(userSurveyAssignment);
		userSurveyAssignment.setSurvey(null);

		return userSurveyAssignment;
	}

}