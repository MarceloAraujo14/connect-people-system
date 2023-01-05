package br.com.connectpeople.resume.repository.mapper;

import br.com.connectpeople.resume.repository.entity.CourseEntity;
import br.com.connectpeople.resume.domain.Course;

import java.util.List;

public class CourseMapper {

    private CourseMapper(){
    }

    public static CourseEntity toCourseEntity(Course course){
        return CourseEntity.builder()
                .name(course.getName())
                .institution(course.getInstitution())
                .type(course.getType())
                .build();
    }

    public static List<CourseEntity> toCourseEntityList(List<Course> courses){
        return courses.stream().map(CourseMapper::toCourseEntity).toList();
    }
}
