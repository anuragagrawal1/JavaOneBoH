package com.sbux.ebs.surveyforms.dao;

import com.sbux.ebs.surveyforms.entity.RoleEntity;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author aburugur
 *
 */
@Lock(LockType.READ)
@Singleton
public class RoleDaoJPA {
	@PersistenceContext(unitName = "sbux-forms-db")
	EntityManager em;
	
	@Lock(LockType.WRITE)
	public void updateRole(RoleEntity updateObject) {
		em.merge(updateObject);
		em.flush();
	}

	@Lock(LockType.WRITE)
	public void createRole(RoleEntity newObject) {
		em.persist(newObject);
		em.flush();
	}

	public RoleEntity getSingleRole(String roleName) {
		Query query = em.createQuery("Select role from RoleEntity role where role.roleName = ?1");
		query.setParameter(1, roleName);

		List<RoleEntity> list = query.getResultList();
		return list.get(0);
	}

	public List<RoleEntity> getAllRoles() {
		Query query = em.createQuery("Select r from RoleEntity r");
		List<RoleEntity> roles = query.getResultList();
		return roles;
	}

}
