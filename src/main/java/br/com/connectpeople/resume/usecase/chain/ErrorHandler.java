package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.resume.domain.exception.InvalidInputException;
import br.com.connectpeople.resume.usecase.input.RegisterResumeInput;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ErrorHandler implements ExecutorChain<RegisterResumeInput> {

    @Override
    public RegisterResumeInput execute(RegisterResumeInput payload) {
        if (Objects.nonNull(payload.getErrors())) {
            throw  new InvalidInputException(payload.getErrors());
        }
        return payload;
    }
}
