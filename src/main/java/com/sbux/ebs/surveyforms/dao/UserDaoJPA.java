package com.sbux.ebs.surveyforms.dao;

import com.sbux.ebs.surveyforms.entity.UserEntity;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author mkodthiw
 *
 */
@Lock(LockType.READ)
@Singleton
public class UserDaoJPA {

	@PersistenceContext(unitName = "sbux-forms-db")
	EntityManager em;

	@Lock(LockType.WRITE)
	public void updateUser(UserEntity updateObject) {
		em.merge(updateObject);
		em.flush();
	}
	
	@Lock(LockType.WRITE)
	public void createUser(UserEntity newObject) {
		em.persist(newObject);
		em.flush();
	}

	public UserEntity getSingleUser(String username) {
		Query query = em.createQuery("Select user from UserEntity user where user.username = ?1");
		query.setParameter(1, username);

		List<UserEntity> list = query.getResultList();
		return list.get(0);
	}

	public List<UserEntity> getAllUsers() {
		Query query = em.createQuery("Select u from UserEntity u");
		List<UserEntity> users = query.getResultList();
		return users;
	}

}
