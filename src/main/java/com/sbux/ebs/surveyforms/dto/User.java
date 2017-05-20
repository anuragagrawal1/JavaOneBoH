package com.sbux.ebs.surveyforms.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

	public User() {
	}

	@XmlElement(name = "username")
	private String username;

	@XmlElement(name = "userid")
	private int userId;

	@XmlElement(name = "createdby")
	private String createdBy;

	@XmlElement(name = "creationdate")
	private Date creationDate;

	@XmlElement(name = "email")
	private String email;

	@XmlElement(name = "isactive")
	private String isActive;

	@XmlElement(name = "isadmin")
	private String isAdmin;

	@XmlElement(name = "issurveyadmin")
	private String isSurveyadmin;

	@XmlElement(name = "lastupdatedate")
	private Date lastUpdateDate;

	@XmlElement(name = "lastupdatedby")
	private String lastUpdatedBy;

	@XmlTransient
	private String passwordHashed;

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
}