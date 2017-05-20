package com.sbux.ebs.surveyforms.business;

import com.sbux.ebs.surveyforms.dao.RoleDaoJPA;
import com.sbux.ebs.surveyforms.dto.Role;
import com.sbux.ebs.surveyforms.entity.RoleEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

@Singleton
public class RoleBaoJPA {

	@EJB
	RoleDaoJPA roleDao;

	public RoleBaoJPA() {
	}

	public boolean updateRole(Role updateObject) {

		try {
			RoleEntity existingRole = roleDao.getSingleRole(updateObject.getRoleName());

			if (StringUtils.isNotEmpty(updateObject.getLastUpdatedBy()))
				existingRole.setLastUpdatedBy(updateObject.getLastUpdatedBy());

			existingRole.setLastUpdateDate(new Date());

			if (StringUtils.isNotEmpty(updateObject.getIsActive()))
				existingRole.setIsActive(updateObject.getIsActive());

			roleDao.updateRole(existingRole);

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean createRole(Role newObject) {
		try {

			RoleEntity newRole = new RoleEntity();

			newRole.setRoleId(getNextId());
			newRole.setCreationDate(new Date());

			if (StringUtils.isNotEmpty(newObject.getCreatedBy()))
				newRole.setCreatedBy(newObject.getCreatedBy());

			if (StringUtils.isNotEmpty(newObject.getIsActive()))
				newRole.setIsActive(newObject.getIsActive());

			if (StringUtils.isNotEmpty(newObject.getRoleName()))
				newRole.setRoleName(newObject.getRoleName());

			roleDao.createRole(newRole);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Role getSingleRole(String rolename) {
		try {
			return ConvertRoleEntityToDTO(roleDao.getSingleRole(rolename));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Role> getAllRoles() {
		try {
			List<Role> getRoles = new ArrayList<>();
			List<RoleEntity> roleEntities = roleDao.getAllRoles();

			roleEntities.forEach((e) -> getRoles.add(ConvertRoleEntityToDTO(e)));

			return getRoles;

		} catch (Exception e) {
			return null;
		}
	}

	public Role ConvertRoleEntityToDTO(RoleEntity roleEntity) {
		Role getRole = new Role();

		getRole.setCreatedBy(roleEntity.getCreatedBy());
		getRole.setCreationDate(roleEntity.getCreationDate());
		getRole.setIsActive(roleEntity.getIsActive());
		getRole.setLastUpdateDate(roleEntity.getLastUpdateDate());
		getRole.setLastUpdatedBy(roleEntity.getLastUpdatedBy());
		getRole.setRoleId(roleEntity.getRoleId());
		getRole.setRoleName(roleEntity.getRoleName());

		return getRole;
	}

	public int getNextId() {
		List<RoleEntity> roles = roleDao.getAllRoles();
		List<Integer> listIds = new ArrayList<>();
		roles.forEach((e) -> listIds.add(e.getRoleId()));
		Collections.sort(listIds);
		return listIds.get(listIds.size() - 1) + 1;
	}

}
