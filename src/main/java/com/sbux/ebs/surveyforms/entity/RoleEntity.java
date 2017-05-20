package com.sbux.ebs.surveyforms.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="RoleEntity.findAll", query="SELECT r FROM RoleEntity r")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_id")
	private int roleId;

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

	@Column(name="role_name")
	private String roleName;

	//bi-directional many-to-one association to RoleAssignmentEntity
	//@OneToMany(mappedBy="role")
	private List<RoleAssignmentEntity> roleAssignments;

	public RoleEntity() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<RoleAssignmentEntity> getRoleAssignments() {
		return this.roleAssignments;
	}

	public void setRoleAssignments(List<RoleAssignmentEntity> roleAssignments) {
		this.roleAssignments = roleAssignments;
	}

	public RoleAssignmentEntity addRoleAssignment(RoleAssignmentEntity roleAssignment) {
		getRoleAssignments().add(roleAssignment);
		roleAssignment.setRole(this);

		return roleAssignment;
	}

	public RoleAssignmentEntity removeRoleAssignment(RoleAssignmentEntity roleAssignment) {
		getRoleAssignments().remove(roleAssignment);
		roleAssignment.setRole(null);

		return roleAssignment;
	}

}