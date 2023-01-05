package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.commons.exception.InvalidInputException;
import br.com.connectpeople.resume.domain.enums.Schooling;
import br.com.connectpeople.resume.usecase.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static br.com.connectpeople.commons.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.commons.constants.Constants.ErrorMessage.ERROR_MSG_VALID_OPTION;
import static br.com.connectpeople.commons.constants.Constants.StateProcess.FAILURE;

@Log4j2
@Component
public class ValidateSchooling implements ExecutorChain<ResumePayload> {

    @Override
    public ResumePayload execute(ResumePayload payload) {
        try {
            inputValidate(payload.getSchooling());
        } catch (InvalidInputException ex) {
            payload.putError(ex.getError(), ex.getMessage());
            log.info("M execute, payload={}, error={}, state={}", payload, ex.getMessage(), FAILURE);
        }
        return payload;
    }

    private static void inputValidate(String schooling) {
        if (Objects.isNull(schooling) || schooling.isBlank()) throw new InvalidInputException("schooling", ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        if (!validSchooling(schooling)){
            throw new InvalidInputException("schooling", ERROR_MSG_VALID_OPTION);
        }
    }

    private static boolean validSchooling(String schooling) {
        try{
            Schooling.valueOf(schooling);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
