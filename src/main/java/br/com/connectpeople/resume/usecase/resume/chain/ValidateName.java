package br.com.connectpeople.resume.usecase.resume.chain;

import br.com.connectpeople.resume.domain.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;
import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_NAME_INVALID;
import static br.com.connectpeople.resume.domain.constants.Constants.StateProcess.FAILURE;
import static br.com.connectpeople.resume.domain.constants.Constants.StateProcess.PROCESSING;

@Log4j2
@Component
public class ValidateName implements ExecutorChain<ResumePayload> {

    public static final String REGEX_FULL_NAME = "^[a-zA-ZJ]+(([',. -][a-zA-ZJ ])?[a-zA-ZJ]*)*$";
    public static final String NAME = "name";

    @Override
    public ResumePayload execute(ResumePayload payload) {
        try {
            inputValidate(payload.getName());
        } catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info("M execute, payload={}, error={}, state={}", payload, ex.getMessage(), FAILURE);
        }
        return payload;
    }

    static void inputValidate(String name){
        if (Objects.isNull(name)) throw new InvalidInputException(NAME, ERROR_MSG_FIELD_CANNOT_BE_NULL);
        if (name.isBlank()) throw new InvalidInputException(NAME, ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        if (!name.trim().contains(" ")) throw new InvalidInputException(NAME, ERROR_MSG_NAME_INVALID);
        if (!name.matches(REGEX_FULL_NAME)) throw new InvalidInputException(NAME, ERROR_MSG_NAME_INVALID);
    }


}
