package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.jobexperience.domain.JobExperience;
import br.com.connectpeople.resume.domain.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_MONTH_INVALID;
import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_MONTH_QUIT_BEFORE_ENTER;
import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_YEAR_INVALID;
import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_YEAR_QUIT_BEFORE_ENTER;
import static br.com.connectpeople.resume.domain.constants.Constants.StateProcess.FAILURE;

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
        try {
            if (jobExperience.getTitle().isBlank()) throw new InvalidInputException("title", ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        }catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info(LOG_BUILDER, payload, ex.getMessage(), FAILURE);
        }
        try {
            if (jobExperience.getDescription().isBlank()) throw new InvalidInputException("description", ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        }catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info(LOG_BUILDER, payload, ex.getMessage(), FAILURE);
        }
        try {
            if (jobExperience.getStartMonth() > 0 && jobExperience.getStartMonth() < 13) throw new InvalidInputException("startMonth", ERROR_MSG_MONTH_INVALID);
        }catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info(LOG_BUILDER, payload, ex.getMessage(), FAILURE);
        }
        try {
            if (jobExperience.getStartYear() < LocalDate.now().getYear() - 100
                    && jobExperience.getStartYear() > LocalDate.now().getYear()) throw new InvalidInputException("startYear", ERROR_MSG_YEAR_INVALID);
        }catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info(LOG_BUILDER, payload, ex.getMessage(), FAILURE);
        }

        //regras:
        //  se é não é o emprego atual:
        // - verificar se endMonth e endYear nao sao null ou empty
        // - verificar se o endYear não é menor que o startYear
        // - verificar se o quando o endYear é igual o startYear, o endMonth é igual ou maior que o startMonth

        if (!jobExperience.isCurrentJob()){
            try {
                if (jobExperience.getEndMonth() > 0
                        && jobExperience.getEndMonth() < 13) throw new InvalidInputException("endtMonth", ERROR_MSG_MONTH_INVALID);
                if (jobExperience.getEndYear() == jobExperience.getStartYear()
                        && jobExperience.getEndMonth() < jobExperience.getStartMonth()) throw new InvalidInputException("endtMonth", ERROR_MSG_MONTH_QUIT_BEFORE_ENTER);
            }catch (InvalidInputException ex){
                payload.putError(ex.getError(), ex.getMessage());
                log.info(LOG_BUILDER, payload, ex.getMessage(), FAILURE);
            }
            try {
                if (jobExperience.getEndYear() < LocalDate.now().getYear() - 100
                        && jobExperience.getStartYear() > LocalDate.now().getYear()) throw new InvalidInputException("endYear", ERROR_MSG_YEAR_INVALID);
                if (jobExperience.getEndYear() < jobExperience.getStartYear()) throw new InvalidInputException("endYear", ERROR_MSG_YEAR_QUIT_BEFORE_ENTER);
            }catch (InvalidInputException ex){
                payload.putError(ex.getError(), ex.getMessage());
                log.info(LOG_BUILDER, payload, ex.getMessage(), FAILURE);
            }
        }

    }

}
