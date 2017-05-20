package com.sbux.ebs.surveyforms.dao;

import com.sbux.ebs.surveyforms.entity.UserSurveyAssignmentEntity;
import com.sbux.ebs.surveyforms.exception.OperationException;
import com.sbux.ebs.surveyforms.exception.QueryNoResultsException;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Singleton
@Lock(LockType.READ)//as it is get service
public class UserSurveyAssignmentDAO {
	@PersistenceContext(unitName="sbux-forms-db")
	EntityManager em;
	
	public List<UserSurveyAssignmentEntity> getSurveyAssignmentWithQuery(String userId,String surveyId,String completionDate) {
		String queryString;
		String queryUserId, querySurveyId, queryCompletionDate;
//		select u from UserSurveyAssignmentEntity u where 1=1 AND u.completionDate <= '4/7/2017' and survey.id=20
//		select * from user_survey_assignments u where 1=1 AND u.user_Id='1649367' AND u.survey_Id='20' AND u.completion_date<=STR_TO_DATE('4/6/2017','%c/%e/%Y %r')
//		the following way is very bad due to possible SQL injection issue
		queryUserId=(userId.trim().length()==0 ? "" : " AND u.userId=" + userId);
//		querySurveyId=(surveyId.trim().length()==0 ? "" : " AND survey.surveyId="+surveyId);
		querySurveyId=(surveyId.trim().length()==0 ? "" : " AND u.survey.surveyId="+surveyId);
		queryCompletionDate=(completionDate.trim().length()==0 ? "" : " AND u.completionDate <='" + parseDate(completionDate)+"'");
		queryString = "select u from UserSurveyAssignmentEntity u where 1=1" + queryUserId + queryCompletionDate + querySurveyId;
		System.out.println("\nIn SurveyAssignmentDAO.getSurveyAssignmentWithQuery " + queryString);
		try{
			return em.createQuery(queryString)
					.getResultList();
		}catch (NoResultException e){
			throw new QueryNoResultsException("9001002");
		}
	}
	
	private java.sql.Timestamp parseDate(String strDate){
		SimpleDateFormat dfDate=new SimpleDateFormat("M/d/yyyy");
		dfDate.setLenient(false);// The parser is funny, if you pass 20/20/2017, it would roll the time to 8/20/2018 - Makes it STRICT
		java.sql.Timestamp sqlDate;
//			System.out.println("\nDate Format Value : "+dfDate.toString()+"");
		java.util.Date uDate=new java.util.Date();
			try {
				System.out.println(strDate);
				
				uDate = dfDate.parse(strDate);
				
			} catch (ParseException e1) {
//				throw new NoResultException();
				throw new OperationException("9001005");
//				e1.printStackTrace();
			}
			System.out.println("\n Util nDate Format Value : "+uDate.toString()+"");
			 sqlDate = new java.sql.Timestamp(uDate.getTime());
			System.out.println("\n SQL Date Format Value : "+sqlDate.toString()+"");
			return sqlDate;
//			queryCompletionDate=(completionDate.trim().length()==0 ? "" : " AND u.completionDate <='" + sqlDate+"'");
//			queryCompletionDate=(completionDate.trim().length()==0 ? "" : " AND u.completionDate <=STR_TO_DATE('" + completionDate+"','%c/%e/%Y %r')");
//		return null;
	}

	@Lock(LockType.WRITE)
	public void createUserAssignments(UserSurveyAssignmentEntity u) {
		System.out.println("Create UserSurveyAssignment: " + u.getUserId());
		em.persist(u);
		em.flush();
	}
}
