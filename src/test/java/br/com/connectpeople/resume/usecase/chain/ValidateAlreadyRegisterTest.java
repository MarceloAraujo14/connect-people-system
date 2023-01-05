package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.resume.repository.ResumeJpaRepository;
import br.com.connectpeople.commons.exception.RegisterAlreadyExistsException;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAlreadyRegisterTest {

    @InjectMocks
    ValidateAlreadyRegister validateAlreadyRegister;
    @Mock
    ResumeJpaRepository resumeJpaRepository;

    @Test
    void executeSuccess(){
        when(resumeJpaRepository.existsByEmail(any())).thenReturn(false);

        ResumePayload payload = validateAlreadyRegister.execute(ResumePayload.builder().email("jhondoe@gmail.com").build());

        verify(resumeJpaRepository, times(1)).existsByEmail(any());
        verifyNoMoreInteractions(resumeJpaRepository);
        assertNull(payload.getErrors());
    }

    @Test
    void shouldThrowWhenEmailAlreadyRegister(){
        when(resumeJpaRepository.existsByEmail(any())).thenReturn(true);
        ResumePayload payload = ResumePayload.builder().email("jhondoe@gmail.com").build();

        assertThrows(RegisterAlreadyExistsException.class, () -> validateAlreadyRegister.execute(payload));

        verify(resumeJpaRepository, times(1)).existsByEmail(any());
        verifyNoMoreInteractions(resumeJpaRepository);
    }

}