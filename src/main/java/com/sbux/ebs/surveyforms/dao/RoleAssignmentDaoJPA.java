package com.sbux.ebs.surveyforms.dao;

import com.sbux.ebs.surveyforms.entity.RoleAssignmentEntity;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

/**
 * @author aburugur
 *
 */

@Singleton
@Lock(LockType.READ)
public class RoleAssignmentDaoJPA {

	@PersistenceContext(unitName = "sbux-forms-db")
	EntityManager em;
	
	@Lock(LockType.WRITE)
	public void updateRoleAssignment(RoleAssignmentEntity updateObject) {
		em.merge(updateObject);
		em.flush();
	}
	
	@Lock(LockType.WRITE)
	public void updateRoleAssignments(List<RoleAssignmentEntity> updateObjectList) {	
		Iterator<RoleAssignmentEntity> it = updateObjectList.iterator();		
		while ( it.hasNext()) {
			RoleAssignmentEntity entity = it.next();
			em.merge(entity);
        }
		em.flush();
	}
	
	@Lock(LockType.WRITE)
	public void createRoleAssignment(RoleAssignmentEntity newObject) {
		em.persist(newObject);
		em.flush();
	}
	
	@Lock(LockType.WRITE)
	public void createRoleAssignments(List<RoleAssignmentEntity> newObjectList) {
	
		Iterator<RoleAssignmentEntity> it = newObjectList.iterator();
		
		while ( it.hasNext()) {
			RoleAssignmentEntity entity = it.next();

			em.persist(entity);
        }
		
		em.flush();
	}

	public List<RoleAssignmentEntity> getRoleAssignments(String userName) {
		Query query = em.createQuery("Select roleAssignment from RoleAssignmentEntity roleAssignment where roleAssignment.user.username = ?1");
		query.setParameter(1, userName);

		List<RoleAssignmentEntity> list = query.getResultList();
		return list;
	}
	
	public RoleAssignmentEntity getRoleAssignment(String userName, String roleName) {
		Query query = em.createQuery("Select roleAssignment from RoleAssignmentEntity roleAssignment where roleAssignment.user.username = ?1 and "
				+ "roleAssignment.role.roleName = ?2");
		query.setParameter(1, userName);
		query.setParameter(2, roleName);

		List<RoleAssignmentEntity> list = query.getResultList();
		return list.get(0);
	}

	public List<RoleAssignmentEntity> getAllRoleAssignments() {
		Query query = em.createQuery("Select r from RoleAssignmentEntity r");
		List<RoleAssignmentEntity> roles = query.getResultList();
		return roles;
	}

}
