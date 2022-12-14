package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.resume.domain.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.input.RegisterResumeInput;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_BIRTH_DATE_BEFORE_AFTER;
import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_BIRTH_DATE_FORMAT;
import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.resume.domain.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_NULL;
import static br.com.connectpeople.resume.domain.constants.Constants.StateProcess.FAILURE;
import static br.com.connectpeople.resume.domain.constants.Constants.StateProcess.PROCESSING;

@Log4j2
@Component
public class ValidateBirthDate implements ExecutorChain<RegisterResumeInput>{

    private static final int MIN_AGE = 16;
    private static final int MAX_AGE = 100;
    public static final String BIRTH_DATE = "birthDate";
    public static final String DD_MM_YYYY = "dd-MM-yyyy";

    @Override
    public RegisterResumeInput execute(RegisterResumeInput payload) {
        log.info("M execute, payload={}, state={}", payload, PROCESSING);
        try {
            inputValidate(payload.getBirthDate());
        } catch (InvalidInputException ex){
            payload.putError(ex.getError(), ex.getMessage());
            log.info("M execute, payload={}, error={}, state={}", payload, ex.getMessage(), FAILURE);
        }
        return payload;
    }

    private static void inputValidate(String birthDate){
        if (Objects.isNull(birthDate)) throw new InvalidInputException(BIRTH_DATE, ERROR_MSG_FIELD_CANNOT_BE_NULL);
        if (birthDate.isBlank()) throw new InvalidInputException(BIRTH_DATE, ERROR_MSG_FIELD_CANNOT_BE_EMPTY);
        LocalDate date;
        try {
            birthDate = birthDate.replace("/", "-");
          date = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern(DD_MM_YYYY));
        } catch (Exception exception){
            throw new InvalidInputException(BIRTH_DATE, ERROR_MSG_BIRTH_DATE_FORMAT);
        }
        if (!isValid(date)) throw new InvalidInputException(BIRTH_DATE, ERROR_MSG_BIRTH_DATE_BEFORE_AFTER);

    }

    private static boolean isValid(LocalDate birthDate){
        LocalDate today = LocalDate.now();
        if (birthDate.isAfter(today)) return false;
        Period age = Period.between(birthDate, today);
        return age.getYears() > MIN_AGE && age.getYears() <= MAX_AGE;
    }
}
