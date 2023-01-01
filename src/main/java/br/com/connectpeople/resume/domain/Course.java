package br.com.connectpeople.resume.domain;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "course")
@NoArgsConstructor
public class Course {

    private String id;

    private String institution;
    private String course;
    private String type;

}
