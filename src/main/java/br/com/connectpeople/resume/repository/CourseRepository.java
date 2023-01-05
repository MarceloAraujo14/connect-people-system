package br.com.connectpeople.resume.repository;

import br.com.connectpeople.resume.repository.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
