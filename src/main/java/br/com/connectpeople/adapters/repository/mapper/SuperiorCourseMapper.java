package br.com.connectpeople.adapters.repository.mapper;

import br.com.connectpeople.adapters.repository.entity.SuperiorCourseEntity;
import br.com.connectpeople.resume.domain.SuperiorCourse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuperiorCourseMapper {

    private SuperiorCourseMapper(){};

    public static SuperiorCourseEntity toEntity(SuperiorCourse superiorCourse){
        return SuperiorCourseEntity.builder()
                .institution(superiorCourse.getInstitution())
                .course(superiorCourse.getCourse())
                .conclusionYear(superiorCourse.getConclusionYear())
                .build();
    }

    public static List<SuperiorCourseEntity> toSuperiorCourseEntityList(List<SuperiorCourse> superiorCourses){
        return superiorCourses.stream().map(SuperiorCourseMapper::toEntity).toList();
    }
}
