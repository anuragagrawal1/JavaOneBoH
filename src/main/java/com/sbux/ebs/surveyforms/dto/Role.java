package com.sbux.ebs.surveyforms.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "role")
@XmlAccessorType(XmlAccessType.FIELD)
public class Role {

	public Role() {
	}

	@XmlElement(name = "rolename")
	private String roleName;

	@XmlElement(name = "roleid")
	private int roleId;

	@XmlElement(name = "createdby")
	private String createdBy;

	@XmlElement(name = "creationdate")
	private Date creationDate;

	@XmlElement(name = "isactive")
	private String isActive;

	@XmlElement(name = "lastupdatedate")
	private Date lastUpdateDate;

	@XmlElement(name = "lastupdatedby")
	private String lastUpdatedBy;

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
}