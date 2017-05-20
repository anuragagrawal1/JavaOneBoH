package com.sbux.ebs.surveyforms.business;

import com.sbux.ebs.surveyforms.dao.UserDaoJPA;
import com.sbux.ebs.surveyforms.dto.User;
import com.sbux.ebs.surveyforms.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

@Singleton
public class UserBaoJPA {
	
	@EJB
	UserDaoJPA userDao;

	public UserBaoJPA() {
	}

	public boolean updateUser(User updateObject) {

		try {
			UserEntity existingUser = userDao.getSingleUser(updateObject.getUsername());

			if (StringUtils.isNotEmpty(updateObject.getLastUpdatedBy()))
				existingUser.setEmail(updateObject.getEmail());

			if (StringUtils.isNotEmpty(updateObject.getIsActive()))
				existingUser.setIsActive(updateObject.getIsActive());

			if (StringUtils.isNotEmpty(updateObject.getUsername()))
				existingUser.setUsername(updateObject.getUsername());

			if (StringUtils.isNotEmpty(updateObject.getLastUpdatedBy()))
				existingUser.setLastUpdatedBy(updateObject.getLastUpdatedBy());

			existingUser.setLastUpdateDate(new Date());

			userDao.updateUser(existingUser);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean createUser(User newObject) {

		try {
			UserEntity newUser = new UserEntity();

			newUser.setUserId(getNextId());
			newUser.setCreationDate(new Date());
			newUser.setLastUpdateDate(new Date());

			if (StringUtils.isNotEmpty(newObject.getIsActive()))
				newUser.setIsActive(newObject.getIsActive());

			if (StringUtils.isNotEmpty(newObject.getUsername()))
				newUser.setUsername(newObject.getUsername());

			if (StringUtils.isNotEmpty(newObject.getEmail()))
				newUser.setEmail(newObject.getEmail());

			if (StringUtils.isNotEmpty(newObject.getCreatedBy()))
				newUser.setCreatedBy(newObject.getCreatedBy());

			if (StringUtils.isNotEmpty(newObject.getPasswordHashed()))
				newUser.setPasswordHashed(newObject.getPasswordHashed());

			userDao.createUser(newUser);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public User getSingleUser(String username) {
		try {

			return ConvertEntityToDTO(userDao.getSingleUser(username));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> getAllUsers() {
		try {
			List<UserEntity> listEntityUsers = userDao.getAllUsers();
			List<User> listUsers = new ArrayList<>();
			listEntityUsers.forEach((e) -> listUsers.add(ConvertEntityToDTO(e)));
			
			return listUsers;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private User ConvertEntityToDTO(UserEntity userEntity)
	{
		User getUser = new User();		

		getUser.setUsername(userEntity.getUsername());
		getUser.setIsActive(userEntity.getIsActive());
		getUser.setUserId(userEntity.getUserId());		
		getUser.setCreationDate(userEntity.getCreationDate());			
		getUser.setLastUpdateDate(userEntity.getLastUpdateDate());
		getUser.setLastUpdatedBy(userEntity.getLastUpdatedBy());
		getUser.setEmail(userEntity.getEmail());
		getUser.setCreatedBy(userEntity.getCreatedBy());

		return getUser;
	}

	public int getNextId() {
		List<UserEntity> users = userDao.getAllUsers();
		List<Integer> listIds = new ArrayList<>();
		users.forEach((e) -> listIds.add(e.getUserId()));
		Collections.sort(listIds);
		return listIds.get(listIds.size() - 1) + 1;
	}

}
