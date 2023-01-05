package br.com.connectpeople.adapters.repository.entity;

import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.domain.enums.Gender;
import br.com.connectpeople.resume.domain.enums.Schooling;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.time.format.DateTimeFormatter;
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
    @Column(name = "cid", nullable = false)
    private String cid;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "gender", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "cell_phone", nullable = false)
    private String cellPhone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "linkedin", nullable = false)
    private String linkedin;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "schooling", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Schooling schooling;

    @Column(name = "job_option_one", nullable = false)
    private String jobOptionOne;
    @Column(name = "job_option_two")
    private String jobOptionTwo;
    @Column(name = "job_option_three")
    private String jobOptionThree;



    public Resume toResume() {
        return Resume.builder()
                .cid(this.cid)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .birthDate(this.birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .gender(String.valueOf(this.gender))
                .phone(this.phone)
                .cellPhone(this.cellPhone)
                .email(this.email)
                .linkedin(this.linkedin)
                .postalCode(this.postalCode)
                .district(this.district)
                .schooling(String.valueOf(this.schooling))
                .jobOptionOne(this.jobOptionOne)
                .jobOptionTwo(this.jobOptionTwo)
                .jobOptionThree(this.jobOptionThree)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResumeEntity that = (ResumeEntity) o;
        return cid != null && Objects.equals(cid, that.cid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}