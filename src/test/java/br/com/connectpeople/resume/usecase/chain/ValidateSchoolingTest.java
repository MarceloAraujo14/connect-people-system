package br.com.connectpeople.resume.usecase.chain;

import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.connectpeople.commons.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.commons.constants.Constants.ErrorMessage.ERROR_MSG_VALID_OPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ValidateSchoolingTest {

    @InjectMocks
    ValidateSchooling validateSchooling;

    @Test
    void shouldThrowWhenSchoolingIsNull(){
        var payload = ResumePayload.builder().schooling(null).build();
        var result = validateSchooling.execute(payload);
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("schooling"));
        assertEquals(ERROR_MSG_FIELD_CANNOT_BE_EMPTY, result.getErrors().get("schooling"));
    }

    @Test
    void shouldThrowInvalidSchooling(){
        var payload = ResumePayload.builder().schooling("PRIMARIO").build();
        var result = validateSchooling.execute(payload);
        assertNotNull(result.getErrors());
        assertTrue(result.getErrors().containsKey("schooling"));
        assertEquals(ERROR_MSG_VALID_OPTION, result.getErrors().get("schooling"));
    }

    @Test
    void validateSuccess(){
        var payload = ResumePayload.builder().schooling("PRIMARIO_COMPLETO").build();
        var result = validateSchooling.execute(payload);
        assertNull(result.getErrors());
    }


}