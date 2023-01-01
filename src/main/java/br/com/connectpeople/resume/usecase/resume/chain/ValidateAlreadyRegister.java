package br.com.connectpeople.resume.usecase.resume.chain;

import br.com.connectpeople.adapters.repository.ResumeJpaRepository;
import br.com.connectpeople.exception.RegisterAlreadyExistsException;
import br.com.connectpeople.resume.usecase.resume.executor.ExecutorChain;
import br.com.connectpeople.resume.usecase.resume.executor.ResumePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_EMAIL_ALREADY_REGISTER;

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