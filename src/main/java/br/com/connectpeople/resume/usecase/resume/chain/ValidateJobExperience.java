package br.com.connectpeople.resume.usecase.resume.chain;

import br.com.connectpeople.resume.domain.JobExperience;
import br.com.connectpeople.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.resume.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.resume.executor.ResumePayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_MONTH_INVALID;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_MONTH_END_BEFORE_START;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_YEAR_INVALID;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_YEAR_END_BEFORE_START;
import static br.com.connectpeople.constants.Constants.StateProcess.FAILURE;

@Log4j2
@Component
public class ValidateJobExperience implements ExecutorChain<ResumePayload> {

    public static final String LOG_BUILDER = "M execute, payload={}, error={}, state={}";

    private static boolean skip(List<JobExperience> jobExperiences){
        return jobExperiences.isEmpty() || jobExperiences.stream().allMatch(Objects::isNull);
    }

    @Override
    public ResumePayload execute(ResumePayload payload) {
        List<JobExperience> jobExperiences = payload.getJobExperiences();
        if (skip(jobExperiences)){
            return payload;
        }
        jobExperiences.forEach(jobExperience -> inputValidate(jobExperience, payload));
        return payload;
    }

    private static void inputValidate(JobExperience jobExperience, ResumePayload payload) {
        validateEmptyField("title", jobExperience.getTitle(), payload);
        validateEmptyField("description", jobExperience.getDescription(), payload);
        starMonthValidate(jobExperience, payload);
        startYearValidate(jobExperience, payload);

        if (!jobExperience.isCurrentJob()){
            validateNotCurrentJob(jobExperience, payload);
        }
    }

    private static void validateNotCurrentJob(JobExperience jobExperience, ResumePayload payload){
        endMonthValidate(jobExperience, payload);
        endYearValidate(jobExperience, payload);
    }

    private static void validateEmptyField(String field, String value, ResumePayload payload){
        try {
            if (value.isBlank()) throw new InvalidInputException(field, ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        }catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info(LOG_BUILDER, payload, ex.getMessage(), FAILURE);
        }
    }

    private static void starMonthValidate(JobExperience jobExperience, ResumePayload payload){
        try {
            if (jobExperience.getStartMonth() < 1 || jobExperience.getStartMonth() > 12)
                throw new InvalidInputException("startMonth", ERROR_MSG_MONTH_INVALID);
            if (jobExperience.getStartYear() == LocalDate.now().getYear()
                && jobExperience.getStartMonth() > LocalDate.now().getMonthValue())
                throw new InvalidInputException("startMonth", ERROR_MSG_MONTH_INVALID);
        }catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info(LOG_BUILDER, payload, ex.getMessage(), FAILURE);
        }
    }

    private static void startYearValidate(JobExperience jobExperience, ResumePayload payload){
        try {
            if (jobExperience.getStartYear() < LocalDate.now().getYear() - 100
                    || jobExperience.getStartYear() > LocalDate.now().getYear())
                throw new InvalidInputException("startYear", ERROR_MSG_YEAR_INVALID);
        }catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info(LOG_BUILDER, payload, ex.getMessage(), FAILURE);
        }
    }

    private static void endMonthValidate(JobExperience jobExperience, ResumePayload payload){
        try {
            if (jobExperience.getEndMonth() < 1
                    || jobExperience.getEndMonth() > 12)
                throw new InvalidInputException("endMonth", ERROR_MSG_MONTH_INVALID);
            if (jobExperience.getEndYear() == jobExperience.getStartYear()
                    && jobExperience.getEndMonth() < jobExperience.getStartMonth())
                throw new InvalidInputException("endMonth", ERROR_MSG_MONTH_END_BEFORE_START);
        }catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info(LOG_BUILDER, payload, ex.getMessage(), FAILURE);
        }
    }

    private static void endYearValidate(JobExperience jobExperience, ResumePayload payload){
        try {
            if (jobExperience.getEndYear() < jobExperience.getStartYear())
                throw new InvalidInputException("endYear", ERROR_MSG_YEAR_END_BEFORE_START);
            if (jobExperience.getEndYear() < LocalDate.now().getYear() - 100
                    || jobExperience.getEndYear() > LocalDate.now().getYear())
                throw new InvalidInputException("endYear", ERROR_MSG_YEAR_INVALID);
        }catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info(LOG_BUILDER, payload, ex.getMessage(), FAILURE);
        }
    }

}
