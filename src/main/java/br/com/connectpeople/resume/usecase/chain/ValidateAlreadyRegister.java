package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.resume.repository.ResumeJpaRepository;
import br.com.connectpeople.commons.exception.RegisterAlreadyExistsException;
import br.com.connectpeople.resume.usecase.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.connectpeople.commons.constants.Constants.ErrorMessage.ERROR_MSG_EMAIL_ALREADY_REGISTER;

@Component
@RequiredArgsConstructor
public class ValidateAlreadyRegister implements ExecutorChain<ResumePayload> {

    private final ResumeJpaRepository resumeJpaRepository;

    @Override
    public ResumePayload execute(ResumePayload payload) {
        if (resumeJpaRepository.existsByEmail(payload.getEmail())){
            throw new RegisterAlreadyExistsException("email", ERROR_MSG_EMAIL_ALREADY_REGISTER);
        }
        return payload;
    }
}
