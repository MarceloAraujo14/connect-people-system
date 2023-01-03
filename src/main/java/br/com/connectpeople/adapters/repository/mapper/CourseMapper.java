package br.com.connectpeople.adapters.repository.mapper;

import br.com.connectpeople.adapters.repository.entity.CourseEntity;
import br.com.connectpeople.resume.domain.Course;

import java.util.List;

public class CourseMapper {

    private CourseMapper(){
    }

    public static CourseEntity toCourseEntity(Course course){
        return CourseEntity.builder()
                .cid(course.getCid())
                .name(course.getName())
                .institution(course.getInstitution())
                .type(course.getType())
                .build();
    }

    public static List<CourseEntity> toCourseEntityList(List<Course> courses){
        return courses.stream().map(CourseMapper::toCourseEntity).toList();
    }
}
