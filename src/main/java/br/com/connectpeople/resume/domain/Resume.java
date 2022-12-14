package br.com.connectpeople.resume.domain;

import br.com.connectpeople.adapters.repository.entity.ResumeEntity;
import br.com.connectpeople.resume.domain.enums.Gender;
import br.com.connectpeople.resume.domain.enums.Schooling;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Resume {

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

    public ResumeEntity toEntity(){
        return ResumeEntity.builder()
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


}
