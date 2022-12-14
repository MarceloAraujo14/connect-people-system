package br.com.connectpeople.util;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class IdGenerator {

    private IdGenerator(){}

    private static final Random generator = new SecureRandom();

    public static String generateId(String prefix){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int number = generator.nextInt(0,99999);
        return String.format("%s%s%5d",prefix, date, number);
    }

}
