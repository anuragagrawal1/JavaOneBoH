package com.sbux.ebs.surveyforms.dao;

import com.sbux.ebs.surveyforms.entity.SurveyEntity;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@Lock(LockType.READ)

public class SurveyDAO {
	@PersistenceContext(unitName="sbux-forms-db")//from persistence.xml
	EntityManager em ;
//	(EntityManager) Persistence.createEntityManagerFactory("sbux-forms-api");
//	factory.createEntityManager();
     
	public SurveyEntity getSurveyById(String surveyID){//should be string input for service
		try{
			return (SurveyEntity) em.createNamedQuery("SurveyEntity.findById")
							 .setParameter("surveyId", Integer.parseInt(surveyID))
							 .getSingleResult();//returns Object hence casting required.
		}catch (NoResultException e){
			return null;
//			throw new RuntimeException("900110101");// should be pointing to a new exception class
		}
	}
//	Return All surveys
	public List<SurveyEntity> getAllSurveys(){
		try{
			return em.createNamedQuery("SurveyEntity.findAllSurveys").getResultList();//No casting where *TO DO* WHY ? Returns List hence no casting required
		}catch (NoResultException e){
//			throw new RuntimeException("900110101");
			return null;
		}
	}
//	Returns all Active Surveys
	public List<SurveyEntity> getAllActiveSurveys(){
		try {
			return em.createNamedQuery("SurveyEntity.findAllActive").getResultList();
		}catch (NoResultException e){
//			throw new RuntimeException("900110101");
			return null;
		}
	}
//	Returns all inactive Surveys
	public List<SurveyEntity> getAllInactiveSurveys(){
		try{
			return em.createNamedQuery("SurveyEntity.findAllInactive").getResultList();			
		}catch (NoResultException e){
//			throw new RuntimeException("900110101");
			return null;
		}
	}

	@Lock(LockType.WRITE)
	public void updateSurveyById(SurveyEntity singleSurveyEntity){
		em.persist(singleSurveyEntity);
		em.flush();
	}
}
