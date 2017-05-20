package com.sbux.ebs.surveyforms.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "roleassignment")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoleAssignment {

	public RoleAssignment() {
	}

	@XmlElement(name = "username")
	private String userName;

    @XmlElement(name="rolename")
	private String roleName;

	@XmlElement(name = "createdby")
	private String createdBy;

	@XmlElement(name = "creationdate")
	private Date creationDate;

	@XmlElement(name = "isactive")
	private String isActive;

	@XmlElement(name = "lastupdatedby")
	private String lastUpdatedBy;

	@XmlElement(name = "lastupdatedate")
	private Date lastUpdateDate;

	@XmlElement(name = "roleassignmentid")
	private int roleAssignmentId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public int getRoleAssignmentId() {
		return roleAssignmentId;
	}

	public void setRoleAssignmentId(int roleAssignmentId) {
		this.roleAssignmentId = roleAssignmentId;
	}
}
