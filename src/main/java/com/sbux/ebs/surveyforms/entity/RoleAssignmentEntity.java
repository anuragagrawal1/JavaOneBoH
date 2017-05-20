package com.sbux.ebs.surveyforms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the role_assignments database table.
 * 
 */
@Entity
@Table(name="role_assignments")
@NamedQuery(name="RoleAssignmentEntity.findAll", query="SELECT r FROM RoleAssignmentEntity r")
public class RoleAssignmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_assignment_id")
	private int roleAssignmentId;

	@Column(name="assignment_type")
	private String assignmentType;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date")
	private Date creationDate;

	@Column(name="is_active")
	private String isActive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update_date")
	private Date lastUpdateDate;

	@Column(name="last_updated_by")
	private String lastUpdatedBy;

	//bi-directional many-to-one association to UserEntity
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;

	//bi-directional many-to-one association to RoleEntity
	@ManyToOne
	@JoinColumn(name="role_id")
	private RoleEntity role;

	public RoleAssignmentEntity() {
	}

	public int getRoleAssignmentId() {
		return this.roleAssignmentId;
	}

	public void setRoleAssignmentId(int roleAssignmentId) {
		this.roleAssignmentId = roleAssignmentId;
	}

	public String getAssignmentType() {
		return this.assignmentType;
	}

	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public UserEntity getUser() {
		return this.user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public RoleEntity getRole() {
		return this.role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

}