package br.com.connectpeople.resume.usecase.resume.chain;

import br.com.connectpeople.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.resume.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.resume.executor.ResumePayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_NAME_INVALID;
import static br.com.connectpeople.constants.Constants.StateProcess.FAILURE;

@Log4j2
@Component
public class ValidateName implements ExecutorChain<ResumePayload> {

    public static final String REGEX_NAME = "^[A-Z][a-z]*( [A-Z][a-z]*)?$";

    static void inputValidate(String field, String value, ResumePayload payload) {
        try {
            if (Objects.isNull(value) || value.isBlank())
                throw new InvalidInputException(field, ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
            if (!value.matches(REGEX_NAME)) throw new InvalidInputException(field, ERROR_MSG_NAME_INVALID);
        } catch (InvalidInputException ex) {
            payload.putError(ex.getError(), ex.getMessage());
            log.info("M execute, payload={}, error={}, state={}", payload, ex.getMessage(), FAILURE);
        }
    }

    @Override
    public ResumePayload execute(ResumePayload payload) {
        inputValidate("firstName", payload.getFirstName(), payload);
        inputValidate("lastName", payload.getLastName(), payload);
        return payload;
    }
}
