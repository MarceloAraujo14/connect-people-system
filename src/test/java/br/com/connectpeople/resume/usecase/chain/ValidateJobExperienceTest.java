package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.jobexperience.domain.JobExperience;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_MONTH_END_BEFORE_START;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_MONTH_INVALID;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_YEAR_END_BEFORE_START;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_YEAR_INVALID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidateJobExperienceTest {

    ValidateJobExperience validateJobExperience = new ValidateJobExperience();

    //não fazer nada se lista de jobExperiences for vazia
    @Test
    void doNothingWhenJobExperienceListEmpty(){
        ResumePayload payload = validateJobExperience.execute(ResumePayload.builder().jobExperiences(Collections.emptyList()).build());
        assertNull(payload.getErrors());
    }

    //erro se titulo não for preenchido
    @Test
    void shouldThrowWhenTitleEmpty(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setTitle("");
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("title"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, result.getErrors().get("title"));
    }

    //erro se descrição nao for preenchida
    @Test
    void shouldThrowWhenDescriptionEmpty(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setDescription("");
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("description"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, result.getErrors().get("description"));
    }

    //erro se mes é menor que 1
    @Test
    void shouldThrowWhenStartMonthLessThanOne(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setStartMonth(0);
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("startMonth"));
        assertEquals(ERROR_MSG_MONTH_INVALID, result.getErrors().get("startMonth"));
    }

    //erro se mes é maior que 12
    @Test
    void shouldThrowWhenStartMonthMoreThanTwelve(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setStartMonth(13);
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("startMonth"));
        assertEquals(ERROR_MSG_MONTH_INVALID, result.getErrors().get("startMonth"));
    }

    //erro se ano de entrada é menor que 100 anos atras
    @Test
    void shouldThrowWhenStartYearIsBeforeHundredYearsAgo(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setStartYear(LocalDate.now().getYear() - 101);
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("startYear"));
        assertEquals(ERROR_MSG_YEAR_INVALID, result.getErrors().get("startYear"));
    }

    //erro se ano de entrada é maior que ano atual
    @Test
    void shouldThrowWhenStartYearIsAfterNow(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setStartYear(LocalDate.now().getYear() + 1);
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("startYear"));
        assertEquals(ERROR_MSG_YEAR_INVALID, result.getErrors().get("startYear"));
    }

    //erro se o ano de entrada é igual o ano atual e mes de entrada é maior que ano atual
    @Test
    void shouldThrowWhenStartYearIsEqualCurrentYearAndStartMonthIsAfterCurrent(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setStartYear(LocalDate.now().getYear());
        jobExperience.setStartMonth(LocalDate.now().getMonthValue() + 1);
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("startMonth"));
        assertEquals(ERROR_MSG_MONTH_INVALID, result.getErrors().get("startMonth"));
    }

    //erro se não é trabalho atual e mes de saida é menor que 1
    @Test
    void shouldThrowWhenEndtMonthLessThanOne(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setCurrentJob(false);
        jobExperience.setEndMonth(0);
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("endMonth"));
        assertEquals(ERROR_MSG_MONTH_INVALID, result.getErrors().get("endMonth"));
    }
    //erro se nao é trabalho atual e mes de saida é maior que 13
    @Test
    void shouldThrowWhenEndtMonthMoreThanTwelve(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setCurrentJob(false);
        jobExperience.setEndMonth(13);
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("endMonth"));
        assertEquals(ERROR_MSG_MONTH_INVALID, result.getErrors().get("endMonth"));
    }
    //erro se nao é trabalho atual e ano de saida é maior que o ano atual
    @Test
    void shouldThrowWhenIsNotCurrentJobEndYearMoreThanCurrentYear(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setCurrentJob(false);
        jobExperience.setEndYear(LocalDate.now().getYear() + 1);
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("endYear"));
        assertEquals(ERROR_MSG_YEAR_INVALID, result.getErrors().get("endYear"));
    }

    //erro se nao é trabalho atual e ano de saida é menor que ano de entrada
    @Test
    void shouldThrowWhenIsNotCurrentJobEndYearMoreThanStarYear(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setCurrentJob(false);
        jobExperience.setEndYear(LocalDate.now().getYear() - 10);
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("endYear"));
        assertEquals(ERROR_MSG_YEAR_END_BEFORE_START, result.getErrors().get("endYear"));
    }

    //erro se nao é trabalho atual, ano de saida é igual ano de entrada e mes de saida é menor que mes de entrada
    @Test
    void shouldThrowWhenIsNotCurrentJobEndYearEqualsStarYearAndEndMonthLessThanStartMonth(){
        JobExperience jobExperience = buildJobExperience();
        jobExperience.setCurrentJob(false);
        jobExperience.setStartYear(LocalDate.now().getYear());
        jobExperience.setEndYear(LocalDate.now().getYear());
        jobExperience.setStartMonth(2);
        jobExperience.setEndMonth(1);
        ResumePayload result = validateJobExperience.execute(ResumePayload.builder().jobExperiences(List.of(jobExperience)).build());
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("endMonth"));
        assertEquals(ERROR_MSG_MONTH_END_BEFORE_START, result.getErrors().get("endMonth"));
    }

    private static JobExperience buildJobExperience(){
        return JobExperience.builder()
                .title("Hoteleiro")
                .description("Hoteleiro")
                .startMonth(2)
                .startYear(LocalDate.now().getYear() - 1)
                .isCurrentJob(true)
                .endMonth(4)
                .endYear(LocalDate.now().getYear())
                .build();
    }
}