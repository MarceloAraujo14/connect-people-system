package br.com.connectpeople.resume.domain.enums;

import lombok.Getter;

@Getter
public enum Schooling {
    PRIMARIO_INCOMPLETO,
    PRIMARIO_COMPLETO,
    FUNDAMENTAL_INCOMPLETO,
    FUNDAMENTAL_COMPLETO,
    MEDIO_INCOMPLETO,
    MEDIO_COMPLETO,
    SUPERIOR_INCOMPLETO,
    SUPERIOR_COMPLETO;

    private static final Schooling[] grades = Schooling.values();

    public static Schooling getGrades(int i){
        return grades[i];
    }
}
