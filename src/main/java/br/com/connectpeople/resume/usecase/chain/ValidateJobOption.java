package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.resume.domain.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static br.com.connectpeople.resume.domain.constants.Constants.StateProcess.FAILURE;

@Log4j2
@Component
public class ValidateJobOption  implements ExecutorChain<ResumePayload> {
    @Override
    public ResumePayload execute(ResumePayload payload) {
        try {
            inputValidate(payload.getJobOptionOne());
        } catch (InvalidInputException ex) {
            payload.putError(ex.getError(), ex.getMessage());
            log.info("M execute, payload={}, error={}, state={}", payload, ex.getMessage(), FAILURE);
        }
        return payload;
    }

    private static void inputValidate(String jobOne) {
        if (Objects.isNull(jobOne) || jobOne.isBlank()) throw new InvalidInputException("jobOptionOne", "Selecione ao menos uma opção.");
    }
}
