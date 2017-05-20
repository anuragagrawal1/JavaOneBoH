package com.sbux.ebs.surveyforms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="UserEntity.findAll", query="SELECT u FROM UserEntity u")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private int userId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date")
	private Date creationDate;

	private String email;

	@Column(name="is_active")
	private String isActive;

	@Column(name="is_admin")
	private String isAdmin;

	@Column(name="is_surveyadmin")
	private String isSurveyadmin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update_date")
	private Date lastUpdateDate;

	@Column(name="last_updated_by")
	private String lastUpdatedBy;

	@Column(name="password_hashed")
	private String passwordHashed;

	private String username;

	//bi-directional many-to-one association to RoleAssignmentEntity
	//@OneToMany(mappedBy="user")
	private List<RoleAssignmentEntity> roleAssignments;

	public UserEntity() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getIsSurveyadmin() {
		return this.isSurveyadmin;
	}

	public void setIsSurveyadmin(String isSurveyadmin) {
		this.isSurveyadmin = isSurveyadmin;
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

	public String getPasswordHashed() {
		return this.passwordHashed;
	}

	public void setPasswordHashed(String passwordHashed) {
		this.passwordHashed = passwordHashed;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<RoleAssignmentEntity> getRoleAssignments() {
		return this.roleAssignments;
	}

	public void setRoleAssignments(List<RoleAssignmentEntity> roleAssignments) {
		this.roleAssignments = roleAssignments;
	}

	public RoleAssignmentEntity addRoleAssignment(RoleAssignmentEntity roleAssignment) {
		getRoleAssignments().add(roleAssignment);
		roleAssignment.setUser(this);

		return roleAssignment;
	}

	public RoleAssignmentEntity removeRoleAssignment(RoleAssignmentEntity roleAssignment) {
		getRoleAssignments().remove(roleAssignment);
		roleAssignment.setUser(null);

		return roleAssignment;
	}

}