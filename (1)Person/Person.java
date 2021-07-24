package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Person {
    private final LocalDate birthDay;
    public enum DatePattern {
        FULL("dd-MM-yyyy"),
        MEDIUM("dd LLLL"),
        SHORT("dd");
        private final String pattern;
        DatePattern(String str) {
            pattern = str;
        }
        public String getPattern() {
            return pattern;
        }
    }
    public Person() {
        birthDay = null;
    }
    public Person(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
    public LocalDate getBirthDay() {
        return birthDay;
    }
    @Override
    public String toString() {
        return "Person{" +
                "birthDay=" + birthDay +
                '}';
    }
    public String toString(DatePattern pattern) {
        switch (pattern) {
            case FULL:
                return birthDay != null ? birthDay.format(DateTimeFormatter.ofPattern(DatePattern.FULL.getPattern())) : null;
            case MEDIUM:
                return birthDay != null ? birthDay.format(DateTimeFormatter.ofPattern(DatePattern.MEDIUM.getPattern(), Locale.ENGLISH)) : null;
            case SHORT:
                return birthDay != null ? birthDay.format(DateTimeFormatter.ofPattern(DatePattern.SHORT.getPattern())) : null;
            default:
                return toString();
        }
    }
}
