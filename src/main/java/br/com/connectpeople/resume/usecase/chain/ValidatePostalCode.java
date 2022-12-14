package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.resume.domain.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.input.RegisterResumeInput;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;
import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_POSTALCODE_INVALID;
import static br.com.connectpeople.resume.domain.constants.Constants.StateProcess.FAILURE;

@Log4j2
@Component
public class ValidatePostalCode implements ExecutorChain<RegisterResumeInput> {

    public static final String POSTAL_CODE = "postalCode";

    @Override
    public RegisterResumeInput execute(RegisterResumeInput payload) {
        try {
            inputValidate(payload.getPostalCode());
        }catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info("M execute, payload={}, error={}, state={}", payload, ex.getMessage(), FAILURE);
        }
        return payload;
    }

    private static void inputValidate(String postalCode) {

        if (Objects.isNull(postalCode)) throw new InvalidInputException(POSTAL_CODE, ERROR_MSG_FIELD_CANNOT_BE_NULL);
        if (postalCode.isBlank()) throw new InvalidInputException(POSTAL_CODE, ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        if(!isValidPostalCode(postalCode)) throw new InvalidInputException(POSTAL_CODE, ERROR_MSG_POSTALCODE_INVALID);
    }

    private static boolean isValidPostalCode(String postalCode){
        if (postalCode.matches("\\w")){
            return false;
        }
        postalCode = postalCode.replaceAll("\\D", "");
        return postalCode.length() == 8;
    }

}
