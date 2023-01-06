package br.com.connectpeople.resume.api.resource;

import br.com.connectpeople.resume.api.request.ResumeRequest;
import br.com.connectpeople.resume.domain.Course;
import br.com.connectpeople.resume.domain.JobExperience;
import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.domain.SuperiorCourse;
import br.com.connectpeople.resume.domain.enums.CourseType;
import br.com.connectpeople.resume.usecase.RegisterResumeUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.connectpeople.commons.constants.Constants.StateProcess.NEW;
import static br.com.connectpeople.commons.constants.Constants.StateProcess.SUCCESS;
import static br.com.connectpeople.resume.domain.enums.SuperiorCourseStatus.CONCLUIDO;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/api/resume")
public class ResumeRestResource {

    private final RegisterResumeUseCase registerResumeUseCase;

    @CrossOrigin(originPatterns = {"*"})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resume register(@Valid @RequestBody ResumeRequest request) {
        log.info("M register, request={}, state={}", request, NEW);
        Resume execute = registerResumeUseCase.execute(request.toResume());
        log.info("M register, request={}, state={}", execute, SUCCESS);
        return execute;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Resume findResume(){
        return Resume.builder()
                .cid("CID20220105")
                .firstName("Jhon")
                .lastName("Doe")
                .gender("M")
                .birthDate("2022-01-01")
                .cellPhone("(21) 99999-9999")
                .email("jhondoe@gmail.com")
                .linkedin("linkedin.com/jhondoe")
                .postalCode("2006")
                .district("Anywhere")
                .city("NeverMore")
                .schooling("SUPERIOR_INCOMPLETO")
                .superiorCourses(
                        List.of(
                                SuperiorCourse.builder()
                                        .course("Superior Course")
                                        .institution("Course Institute")
                                        .status(CONCLUIDO)
                                        .conclusionYear(2020)
                                        .build()
                        )
                )
                .courses(
                        List.of(
                                Course.builder()
                                        .name("Course")
                                        .institution("Course Insitute")
                                        .type(CourseType.LIVRE)
                                        .build()
                        )
                )
                .jobExperiences(
                        List.of(
                                JobExperience.builder()
                                        .title("First Job")
                                        .company("Company")
                                        .startMonth(1)
                                        .startYear(1999)
                                        .currentJob(true)
                                        .description("Description")
                                        .build()
                        )
                )
                .jobOptionOne("Camareiro")
                .build();
    }

}
