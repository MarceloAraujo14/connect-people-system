package br.com.connectpeople.resume.usecase.resume.chain;

import br.com.connectpeople.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.resume.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.resume.executor.ResumePayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_EMAIL_INVALID;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.constants.Constants.StateProcess.FAILURE;

@Log4j2
@Component
public class ValidateEmail implements ExecutorChain<ResumePayload> {

    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)?@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String EMAIL = "email";

    @Override
    public ResumePayload execute(ResumePayload payload) {
        try {
            inputValidate(payload.getEmail());
        } catch (InvalidInputException ex) {
            payload.putError(ex.getError(), ex.getMessage());
            log.info("M execute, payload={}, error={}, state={}", payload, ex.getMessage(), FAILURE);
        }
        return payload;
    }

    private static void inputValidate(String email) {
        if (Objects.isNull(email) || email.isBlank()) throw new InvalidInputException(EMAIL, ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        if (email.trim().contains(" ") || !email.matches(EMAIL_REGEX))
            throw new InvalidInputException(EMAIL, ERROR_MSG_EMAIL_INVALID);
    }

}
