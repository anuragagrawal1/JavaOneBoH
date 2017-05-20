package com.sbux.ebs.surveyforms.business;

import com.sbux.ebs.surveyforms.dao.QuestionDao;
import com.sbux.ebs.surveyforms.dto.OptionChoiceDTO;
import com.sbux.ebs.surveyforms.dto.QuestionDTO;
import com.sbux.ebs.surveyforms.dto.Questions;
import com.sbux.ebs.surveyforms.entity.OptionChoiceEntity;
import com.sbux.ebs.surveyforms.entity.SurveyQuestionEntity;
import com.sbux.ebs.surveyforms.dto.Error;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class QuestionBusiness {

	@EJB
	QuestionDao questionDao;

	public Questions getAllQuestions(int surveyId, String option) {
		List<SurveyQuestionEntity> q = questionDao.getAllQuestions(surveyId);
		Questions questions = new Questions();
		System.out.println("In method getAllQuestions...");
			List<QuestionDTO> questionDTO = new ArrayList<QuestionDTO>();
			for (SurveyQuestionEntity qe : q)
				questionDTO.add(getQuestion(qe));
			questions.setCompleteQuestions(questionDTO);
			return questions;
	}
	
	public Questions getAllQuestionsByEnabledFlag(int surveyId, String option,String enabledFlag ) {
		List<SurveyQuestionEntity> q = questionDao.getAllQuestionsByEnabledFlag(surveyId,enabledFlag);
		Questions questions = new Questions();
		System.out.println("In method getAllQuestions...");
			List<QuestionDTO> questionDTO = new ArrayList<QuestionDTO>();
			for (SurveyQuestionEntity qe : q)
				questionDTO.add(getQuestion(qe));
			questions.setCompleteQuestions(questionDTO);
			return questions;
	}

	private QuestionDTO getQuestion(SurveyQuestionEntity qe) {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setQuestionId(qe.getQuestionId());
		questionDTO.setQuestionText(qe.getQuestionText());
		questionDTO.setInputTypeText(qe.getInputType().getInputTypeName());
		questionDTO.setOptionChoices(getOptionChoicesList(qe));
		questionDTO.setQuestionRequiredYN(transformValue(String.valueOf(qe.getQuestionRequiredYn())));
		questionDTO
				.setAllowMultipleOptionAnswersYN(transformValue(String.valueOf(qe.getAllowMultipleOptionAnswersYn())));
		questionDTO.setEnable_Flag(qe.getEnabledFlag());
		return questionDTO;
	}

	public QuestionDTO getQuestionDTO(int surveyId, int questionId) {
		QuestionDTO questionDTO = new QuestionDTO();
		SurveyQuestionEntity sq = questionDao.getQuestion(surveyId, questionId);
		questionDTO = getQuestion(sq);
		return questionDTO;
	}

	// gets List of Required Questions in a Survey
	public Questions getRequiredQuestions(int surveyId) {
		Questions requiredQuestions = new Questions();
		List<QuestionDTO> completeQuestionList = new ArrayList<QuestionDTO>();
		List<SurveyQuestionEntity> sq = questionDao.getRequiredQuestions(surveyId);
		for (SurveyQuestionEntity qe : sq)
			completeQuestionList.add(getQuestion(qe));
		requiredQuestions.setCompleteQuestions(completeQuestionList);
		return requiredQuestions;
	}

	private List<OptionChoiceDTO> getOptionChoicesList(SurveyQuestionEntity qe) {
		List<OptionChoiceDTO> optionChoiceDTOList = new ArrayList<OptionChoiceDTO>();
		for (OptionChoiceEntity oc : qe.getOptionChoices())
			optionChoiceDTOList.add(getOptionChoice(oc));
		return optionChoiceDTOList;
	}

	private OptionChoiceDTO getOptionChoice(OptionChoiceEntity oc) {
		OptionChoiceDTO optionChoiceDTO = new OptionChoiceDTO();
		optionChoiceDTO.setOptionChoiceId(oc.getOptionChoiceId());
		optionChoiceDTO.setOptionChoiceName(oc.getOptionChoiceName());
		return optionChoiceDTO;
	}

	private String transformValue(String intialValue) {
		String finalValue = null;
		if (intialValue == "1")
			finalValue = "Y";
		else
			finalValue = "N";
		return finalValue;
	}

	private Byte transformStringToByte(String intialValue) {
		Byte finalValue = null;
		if (intialValue == "Y")
			finalValue = 1;
		else
			finalValue = 0;
		return finalValue;
	}

	private List<OptionChoiceEntity> getOptionChoicesEntity(QuestionDTO cq) {
		List<OptionChoiceEntity> optionChoicesEntityList = new ArrayList<OptionChoiceEntity>();
		for (OptionChoiceDTO oc : cq.getOptionChoices())
			optionChoicesEntityList.add(getOptionChoiceEntity(oc));
		return optionChoicesEntityList;
	}

	private OptionChoiceEntity getOptionChoiceEntity(OptionChoiceDTO oc) {
		OptionChoiceEntity oe = new OptionChoiceEntity();
		oe.setOptionChoiceName(oc.getOptionChoiceName());
		return oe;
	}

	public void createQuestion(int surveyId, QuestionDTO completeQuestion) {
		System.out.println("Creating Complete Question...");
		questionDao.createUpdateQuestion(createQuestionEntity(surveyId, completeQuestion));
	}

	private SurveyQuestionEntity createQuestionEntity(int surveyId, QuestionDTO questionDTO) {

		SurveyQuestionEntity qe = new SurveyQuestionEntity();
		qe.setQuestionText(questionDTO.getQuestionText());
		qe.setEnabledFlag("1");
		qe.setInputType(questionDao.getInputTypeId(questionDTO.getInputTypeText()));
		qe.setSurveyId(surveyId);
		qe.setQuestionRequiredYn(transformStringToByte(questionDTO.getQuestionRequiredYN()));
		qe.setAllowMultipleOptionAnswersYn(transformStringToByte(questionDTO.getAllowMultipleOptionAnswersYN()));
		qe.setOptionChoices(getOptionChoicesEntity(questionDTO));
		return qe;
	}

	public void updateQuestionStatus(int surveyId, int questionId,String enableFLag) {
		SurveyQuestionEntity sq = questionDao.getQuestionBySurveyId(surveyId, questionId);
		sq.setEnabledFlag(enableFLag);
		questionDao.createUpdateQuestion(sq);
	}

	public Error getErrorMessage(Throwable t) {
		String errorCode = t.getMessage();
		System.out.println("Error Code: " + errorCode);
		Error error = new Error();
		error.setCode(errorCode);
		error.setMessage("Unknown Error");
		if ("9001001".equals(errorCode))
			error.setMessage("No item matches the search criteria provided.");
		else if ("9001002".equals(errorCode))
			error.setMessage("No Question found.");
		else
			error.setMessage("Unknown Error: Contact System Administrator.");
		return error;
	}
}
