package com.sbux.ebs.surveyforms.dao;

import com.sbux.ebs.surveyforms.entity.InputTypeEntity;
import com.sbux.ebs.surveyforms.entity.SurveyQuestionEntity;
import com.sbux.ebs.surveyforms.exception.QueryNoResultsException;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Singleton
@Lock(LockType.READ)
public class QuestionDao {

	@PersistenceContext(unitName = "sbux-forms-db")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<SurveyQuestionEntity> getAllQuestions(int surveyId) {	
		return em.createNamedQuery("SurveyQuestionEntity.findBySurveyId").setParameter("surveyId", surveyId)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<SurveyQuestionEntity> getAllQuestionsByEnabledFlag(int surveyId, String enabledFlag) {	
		return em.createNamedQuery("SurveyQuestionEntity.findBySurveyIdByEnabledFlag").setParameter("surveyId", surveyId)
				.setParameter("enabledFlag",enabledFlag).getResultList();
	}
	
	public SurveyQuestionEntity getQuestionBySurveyId(int surveyId, int questionId) {
		
		try {
			return (SurveyQuestionEntity) em.createNamedQuery("SurveyQuestionEntity.findBySurveyIdAndQuestionId")
					.setParameter("surveyId", surveyId).setParameter("questionId", questionId).getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			throw new QueryNoResultsException("9001002");
		}
	}
		

	public SurveyQuestionEntity getQuestion(int surveyId, int questionId) {
		
		try {
			return (SurveyQuestionEntity) em.createNamedQuery("SurveyQuestionEntity.findBySurveyIdQuesId")
					.setParameter("surveyId", surveyId).setParameter("questionId", questionId)
					.setParameter("enabledFlag", "1").getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			throw new QueryNoResultsException("9001002");
		}
	}
	
	public InputTypeEntity getInputTypeId(String inputTypeName) {
		return (InputTypeEntity) em.createNamedQuery("InputTypeEntity.findInputTypeId")
				.setParameter("inputTypeName", inputTypeName).getSingleResult();
				
	}
	
	@SuppressWarnings("unchecked")
	public List <SurveyQuestionEntity> getRequiredQuestions(int surveyId){
		try {	
		return em.createNamedQuery("SurveyQuestionEntity.findRequiredQuestionsBySurveyId")
				.setParameter("surveyId", surveyId)
				.setParameter("enabledFlag", "1")
				.setParameter("questionRequiredYn", 1)				
				.getResultList();
		} catch (javax.persistence.NoResultException e) {
			throw new QueryNoResultsException("9001002");
		}
	}
	
	@Lock(LockType.WRITE)
	public void createUpdateQuestion(SurveyQuestionEntity e) {
		System.out.println("Create/Update Question::::::: ");
	em.persist(e);
	em.flush();
	}

}
