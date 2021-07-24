package com.company;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateCalendarGeneratorTest {

    @Test
    void VeryOldDateTest() {
        assertEquals("DateCalendarGenerator{date=12.12.1234 10:10, calendar=12.12.1234 10:10}",
                new DateCalendarGenerator("1234 12 12 10 10").toString());
    }

    @Test
    void PresentDateTest() {
        assertEquals("DateCalendarGenerator{date=21.10.2020 10:11, calendar=21.10.2020 10:11}",
                new DateCalendarGenerator("2020 10 21 10 11").toString());
    }

    @Test
    void FutureDateTest() {
        assertEquals("DateCalendarGenerator{date=21.10.2999 10:11, calendar=21.10.2999 10:11}",
                new DateCalendarGenerator("2999 10 21 10 11").toString());
    }

    @Test
    void IncorrectDateTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new DateCalendarGenerator("2021 21 21 12 08");});
    }

    @Test
    void IncorrectTimeTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new DateCalendarGenerator("2021 10 21 -6 09");});
    }

}
