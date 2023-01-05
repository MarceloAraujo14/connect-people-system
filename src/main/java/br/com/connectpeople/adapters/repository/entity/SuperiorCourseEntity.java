package br.com.connectpeople.adapters.repository.entity;

import br.com.connectpeople.resume.domain.enums.SuperiorCourseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity(name = "superior_couse")
@NoArgsConstructor
public class SuperiorCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String cid;
    private String institution;
    private String course;
    @Enumerated(EnumType.STRING)
    private SuperiorCourseStatus status;
    private int conclusionYear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SuperiorCourseEntity that = (SuperiorCourseEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
