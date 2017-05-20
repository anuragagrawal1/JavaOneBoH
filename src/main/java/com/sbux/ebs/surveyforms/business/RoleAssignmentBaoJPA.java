package com.sbux.ebs.surveyforms.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import com.sbux.ebs.surveyforms.dao.RoleAssignmentDaoJPA;
import com.sbux.ebs.surveyforms.dao.RoleDaoJPA;
import com.sbux.ebs.surveyforms.dao.UserDaoJPA;
import com.sbux.ebs.surveyforms.dto.RoleAssignment;
import com.sbux.ebs.surveyforms.entity.RoleAssignmentEntity;

@Singleton
public class RoleAssignmentBaoJPA {
	
	@EJB
	RoleAssignmentDaoJPA roleAssignmentDao;
	
	@EJB
	RoleDaoJPA roleDao;
	
	@EJB
	UserDaoJPA userDao;

	public RoleAssignmentBaoJPA() {
	}

	public boolean updateRoleAssignments(RoleAssignment inputObject) {

		try {

			RoleAssignmentEntity updateObject = roleAssignmentDao.getRoleAssignment(inputObject.getUserName(), inputObject.getRoleName());
			
			updateObject.setLastUpdateDate(new Date());
			updateObject.setLastUpdatedBy(inputObject.getLastUpdatedBy());
			updateObject.setIsActive(inputObject.getIsActive());
			
			updateObject.setRole(roleDao.getSingleRole(inputObject.getRoleName()));
			
			updateObject.setAssignmentType(inputObject.getRoleName());
			
			roleAssignmentDao.updateRoleAssignment(updateObject);
			
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean createRoleAssignment(RoleAssignment inputObject) {
		try {
			RoleAssignmentEntity newObject = new RoleAssignmentEntity();
			newObject.setRoleAssignmentId(getNextId());
			newObject.setCreationDate(new Date());
			newObject.setCreatedBy(inputObject.getCreatedBy());
			newObject.setAssignmentType(inputObject.getRoleName());
			newObject.setIsActive(inputObject.getIsActive());
			newObject.setRole(roleDao.getSingleRole(inputObject.getRoleName()));
			newObject.setUser(userDao.getSingleUser(inputObject.getUserName()));
			
			roleAssignmentDao.createRoleAssignment(newObject);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<RoleAssignment> getSingleRoleAssignment(String userName) {
		try {
			List<RoleAssignmentEntity> getAssignments = roleAssignmentDao.getRoleAssignments(userName);
			List<RoleAssignment> listAssignmentsDto = new ArrayList<>();
			getAssignments.forEach((e) -> listAssignmentsDto.add(ConvertRoleAssignmentEntityToDTO(e)));
			
			return listAssignmentsDto;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public List<RoleAssignment> getAllRoleAssignments() {
		try {
			List<RoleAssignmentEntity> getAssignments = roleAssignmentDao.getAllRoleAssignments();
			List<RoleAssignment> listAssignmentsDto = new ArrayList<>();
			getAssignments.forEach((e) -> listAssignmentsDto.add(ConvertRoleAssignmentEntityToDTO(e)));
			
			return listAssignmentsDto;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public RoleAssignment ConvertRoleAssignmentEntityToDTO(RoleAssignmentEntity reqAssign) {
		try {
			RoleAssignment returnAssign = new RoleAssignment();

			returnAssign.setCreatedBy(reqAssign.getCreatedBy());
			returnAssign.setCreationDate(reqAssign.getCreationDate());
			returnAssign.setIsActive(reqAssign.getIsActive());
			returnAssign.setLastUpdateDate(reqAssign.getLastUpdateDate());
			returnAssign.setLastUpdatedBy(reqAssign.getLastUpdatedBy());
			returnAssign.setRoleAssignmentId(reqAssign.getRoleAssignmentId());
			returnAssign.setRoleName(reqAssign.getRole().getRoleName());
			returnAssign.setUserName(reqAssign.getUser().getUsername());

			return returnAssign;
			// userDao.getSingleUser(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getNextId() {
		List<RoleAssignmentEntity> roles = roleAssignmentDao.getAllRoleAssignments();
		List<Integer> listIds = new ArrayList<>();
		roles.forEach((e) -> listIds.add(e.getRoleAssignmentId()));
		Collections.sort(listIds);
		return listIds.get(listIds.size() - 1) + 1;
	}
}
