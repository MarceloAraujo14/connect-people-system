package br.com.connectpeople.resume.domain;

import br.com.connectpeople.resume.domain.enums.CourseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private Long id;

    private String cid;
    private String institution;
    private String name;
    private CourseType type;


}
