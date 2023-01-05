package br.com.connectpeople.adapters.repository;

import br.com.connectpeople.adapters.repository.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
