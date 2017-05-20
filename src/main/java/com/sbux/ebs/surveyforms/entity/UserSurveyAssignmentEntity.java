package com.sbux.ebs.surveyforms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_survey_assignments database table.
 * 
 */
@Entity
@Table(name="user_survey_assignments")
@NamedQuery(name="UserSurveyAssignmentEntity.findAll", query="SELECT u FROM UserSurveyAssignmentEntity u")
public class UserSurveyAssignmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="survey_assignment_id")
	private int surveyAssignmentId;

	@Temporal(TemporalType.DATE)
	@Column(name="assignment_date")
	private Date assignmentDate;

	@Temporal(TemporalType.DATE)
	@Column(name="completion_date")
	private Date completionDate;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to SurveyEntity
	@ManyToOne
	@JoinColumn(name="survey_id")
	private SurveyEntity survey;

	public UserSurveyAssignmentEntity() {
	}

	public int getSurveyAssignmentId() {
		return this.surveyAssignmentId;
	}

	public void setSurveyAssignmentId(int surveyAssignmentId) {
		this.surveyAssignmentId = surveyAssignmentId;
	}

	public Date getAssignmentDate() {
		return this.assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public Date getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
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