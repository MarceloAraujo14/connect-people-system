package br.com.connectpeople.adapters.repository.entity;

import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.domain.enums.Gender;
import br.com.connectpeople.resume.domain.enums.Schooling;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@NoArgsConstructor
@Entity
@Table(name = "resume")
public class ResumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String phone;
    private String cellPhone;
    private String email;
    private String cep;
    private String district;
    private String jobOptionOne;
    private String jobOptionTwo;
    private String jobOptionThree;
    private String jobExpOne;
    private String jobExpTwo;
    private String jobExpThree;
    private Schooling schooling;


    public Resume toResume(){
        return Resume.builder()
                .name(this.name)
                .birthDate(this.birthDate)
                .gender(this.gender)
                .phone(this.phone)
                .cellPhone(this.cellPhone)
                .email(this.email)
                .cep(this.cep)
                .district(this.district)
                .jobOptionOne(this.jobOptionOne)
                .jobOptionTwo(this.jobOptionTwo)
                .jobOptionThree(this.jobOptionThree)
                .jobExpOne(this.jobExpOne)
                .jobExpTwo(this.jobExpTwo)
                .jobExpThree(this.jobExpThree)
                .schooling(this.schooling)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResumeEntity that = (ResumeEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}