package com.sbux.ebs.surveyforms.dao;

import com.sbux.ebs.surveyforms.entity.AnswerEntity;
import com.sbux.ebs.surveyforms.exception.QueryNoResultsException;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@Lock(LockType.READ)
public class AnswerDAO {
	@PersistenceContext(unitName = "sbux-forms-db")
	EntityManager em;
	
	@Lock(LockType.WRITE)
	public void createAnswer(AnswerEntity a) {
		System.out.println("Create Answer: " + a.getAnswerId());
	em.persist(a);
	em.flush();
	}	 
	
	@Lock(LockType.READ)
	public List<AnswerEntity> getAnswersBySurveyId(String surveyId){
		try {
			return em.createNamedQuery("AnswerEntity.findBySurveyId")
					.setParameter("surveyId", Integer.parseInt(surveyId))
					.getResultList();
		}catch (NoResultException e){
			throw new QueryNoResultsException("9001002");
		}
	}
}