package br.com.connectpeople.resume.usecase.resume.chain;

import br.com.connectpeople.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.resume.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.resume.executor.ResumePayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_PHONENUMBER_INVALID;
import static br.com.connectpeople.constants.Constants.StateProcess.FAILURE;

@Log4j2
@Component
public class ValidatePhone implements ExecutorChain<ResumePayload> {

    public static final String PHONE_NUMBER = "phoneNumber";

    private static void inputValidate(String phone) {
        if (Objects.isNull(phone) || phone.isBlank()) throw new InvalidInputException(PHONE_NUMBER, ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        if (!isValidPhoneNumber(phone)) throw new InvalidInputException(PHONE_NUMBER, ERROR_MSG_PHONENUMBER_INVALID);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Removing any non-digit character from the phone number
        phoneNumber = phoneNumber.replaceAll("\\D", "");

        // Checking if the phone number has 11 or 10 digits
        if (phoneNumber.length() != 11 && phoneNumber.length() != 10) {
            return false;
        }

        // The first two digits of the phone number represent the DDD
        int ddd = Integer.parseInt(phoneNumber.substring(0, 2));

        // Validating the DDD
        if (ddd < 11 || ddd > 99) {
            return false;
        }

        // The remaining digits of the phone number represent the subscriber number
        String subscriberNumber = phoneNumber.substring(2);

        // Validating the subscriber number
        return subscriberNumber.matches("\\d{8,9}");
    }

    @Override
    public ResumePayload execute(ResumePayload payload) {
        try {
            inputValidate(payload.getCellPhone());
            if (!isValidPhoneNumber(payload.getPhone()))
                throw new InvalidInputException(PHONE_NUMBER, ERROR_MSG_PHONENUMBER_INVALID);
        } catch (InvalidInputException ex) {
            payload.putError(ex.getError(), ex.getMessage());
            log.info("M execute, payload={}, error={}, state={}", payload, ex.getMessage(), FAILURE);
        }
        return payload;
    }

}
