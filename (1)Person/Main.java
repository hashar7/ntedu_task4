package com.company;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        LocalDate d = LocalDate.now();
	    Person p = new Person(d);
	    System.out.println(p.toString());
	    System.out.println(p.toString(Person.DatePattern.FULL));
        System.out.println(p.toString(Person.DatePattern.MEDIUM));
        System.out.println(p.toString(Person.DatePattern.SHORT));
        /* OUTPUT
        Person{birthDay=2021-07-24}
        24-07-2021
        24 July
        24
         */
    }
}

