package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.jobexperience.domain.JobExperience;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, result.getErrors().get("title"));
    }
    //erro se descrição nao for preenchida

    //erro se mes é menor que 1

    //erro se mes é maior que 12

    //erro se ano de entrada é menor que 100 anos atras

    //erro se ano de entrada é maior que ano atual

    //erro se o ano de entrada é igual o ano atual e mes de entrada é maior que ano atual

    //erro se não é trabalho atual e mes de saida é menor que 1

    //erro se nao é trabalho atual e mes de saida é maior que 13

    //erro se nao é trabalho atual e ano de saida é maior que o ano atual

    //erro se nao é trabalho atual e ano de saida é menor que ano de entrada

    //erro se nao é trabalho atual, ano de saida é igual ano de entrada e mes de saida é menor que mes de entrada

    private static JobExperience buildJobExperience(){
        return JobExperience.builder()
                .title("Hoteleiro")
                .description("Hoteleiro")
                .startMonth(2)
                .startYear(2020)
                .isCurrentJob(false)
                .endMonth(4)
                .endYear(2021)
                .build();
    }
}