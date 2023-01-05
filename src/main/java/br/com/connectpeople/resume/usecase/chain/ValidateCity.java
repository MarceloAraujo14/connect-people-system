package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.commons.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static br.com.connectpeople.commons.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.commons.constants.Constants.StateProcess.FAILURE;

@Log4j2
@Component
public class ValidateCity implements ExecutorChain<ResumePayload> {

    @Override
    public ResumePayload execute(ResumePayload payload) {
        try {
            inputValidate(payload.getCity());
        } catch (InvalidInputException ex) {
            payload.putError(ex.getError(), ex.getMessage());
            log.info("M execute, payload={}, error={}, state={}", payload, ex.getMessage(), FAILURE);
        }
        return payload;
    }

    private static void inputValidate(String city) {
        if (Objects.isNull(city) || city.isBlank()) throw new InvalidInputException("city", ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
    }
}
