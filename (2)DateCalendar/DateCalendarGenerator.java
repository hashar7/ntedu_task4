package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DateCalendarGenerator {
    private Date date;
    private Calendar calendar;
    public DateCalendarGenerator(Date date, Calendar calendar) {
        this.date = date;
        this.calendar = calendar;
    }
    public DateCalendarGenerator() throws IllegalArgumentException {
        System.out.println("Enter the date in format: year<4 digits> month<1-2 digit(s), 1 - January ... 12 - December> day<1-2 digit(s)>");
        Scanner input = new Scanner(System.in);
        int year    = input.nextInt();
        int month   = input.nextInt();
        int day     = input.nextInt();
        System.out.println("Enter the time in format: hours<1-2 digit(s), from 0 to 23> minutes<1-2 digit(s), from 0 to 59>");
        int hours   = input.nextInt();
        int minutes = input.nextInt();
        if (year < 0 || month > 12 || month < 1 || day < 0 || day > 31 || hours > 23 || hours < 0 || minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException();
        }
        date = new Date(year - 1900, month - 1, day, hours, minutes);
        calendar = new GregorianCalendar();
        calendar.set(year, month - 1, day, hours, minutes);
    }
    public DateCalendarGenerator(String in) throws IllegalArgumentException {
        String[] split = in.split(" ");
        if (split.length != 5) {
            throw new  IllegalArgumentException();
        }
        int year    = Integer.parseInt(split[0]);
        int month   = Integer.parseInt(split[1]);
        int day     = Integer.parseInt(split[2]);
        int hours   = Integer.parseInt(split[3]);
        int minutes = Integer.parseInt(split[4]);
        if (year < 0 || month > 12 || month < 1 || day < 0 || day > 31 || hours > 23 || hours < 0 || minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException();
        }
        date = new Date(year - 1900, month - 1, day, hours, minutes);
        calendar = new GregorianCalendar();
        calendar.set(year, month - 1, day, hours, minutes);
    }
    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return "DateCalendarGenerator{" +
                "date=" + formatter.format(date) + ", " +
                "calendar=" + formatter.format(calendar.getTime()) +
                '}';
    }
}

