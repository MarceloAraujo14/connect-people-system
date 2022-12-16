package br.com.connectpeople.jobexperience.usecase;

import br.com.connectpeople.adapters.repository.JobExperienceJpaRepository;
import br.com.connectpeople.adapters.repository.entity.JobExperienceEntity;
import br.com.connectpeople.jobexperience.domain.JobExperience;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static br.com.connectpeople.util.IdGenerator.generateId;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegisterJobExperiencesUseCaseTest {

    @InjectMocks
    RegisterJobExperiencesUseCase registerJobExperiencesUseCase;
    @Mock
    JobExperienceJpaRepository jobExperienceJpaRepository;
    @Captor
    ArgumentCaptor<List<JobExperienceEntity>> entitiesCaptor;

    //verificar se o cid foi setado nos jobExperienceEntities e foram salvos
    @Test
    void shouldSaveSuccess(){
        var jobList = List.of(buildJobExperience());
        registerJobExperiencesUseCase.execute(generateId("CID"), jobList);
        verify(jobExperienceJpaRepository, times(1)).saveAll(entitiesCaptor.capture());

        assertNotNull(entitiesCaptor.getValue().get(0).getCid());
        assertTrue(entitiesCaptor.getValue().get(0).getCid().contains("CID"));
    }

    private static JobExperience buildJobExperience(){
        return JobExperience.builder()
                .title("Hoteleiro")
                .description("Hoteleiro")
                .startMonth(2)
                .startYear(LocalDate.now().getYear() - 1)
                .isCurrentJob(true)
                .endMonth(4)
                .endYear(LocalDate.now().getYear())
                .build();
    }



}