package br.com.connectpeople.resume.usecase.resume.chain;

import br.com.connectpeople.resume.domain.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ErrorHandler implements ExecutorChain<ResumePayload> {

    @Override
    public ResumePayload execute(ResumePayload payload) {
        if (Objects.nonNull(payload.getErrors())) {
            throw  new InvalidInputException(payload.getErrors());
        }
        return payload;
    }
}
