package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringAnalyzerTest {

    @Test
    void ContainInBothStringsAndUsualOrderTest() {
        StringAnalyzer sa = new StringAnalyzer("qweyr yuerur ye w uywe iiow pqe9382ie ",
                "dhjsasjksd sjkda   akjsd yu 7777");
        Set<Character> expected = new LinkedHashSet<>();
        expected.add(' ');
        expected.add('u');
        expected.add('y');
        assertEquals(expected.toString(), sa.DefineSortingOrder(sa.ContainInBothStrings(), "USUAL").toString());
    }

    @Test
    void ContainInFirstAbsentInSecondAndReverseOrderTest() {
        StringAnalyzer sa = new StringAnalyzer("utioyrq ririe urtw oyor iye utiorityity",
                "tr o ti oittuiru iyopy 9889");
        Set<Character> expected = new LinkedHashSet<>();
        for (char c : new char[]{'w', 'q', 'e'}) {
            expected.add(c);
        }
        assertEquals(expected.toString(), sa.DefineSortingOrder(sa.ContainInFirstAbsentInSecond(), "REVERSE").toString());
    }

    @Test
    void ContainInAtLeastOneStringAndHashOffsetOrderTest() {
        StringAnalyzer sa = new StringAnalyzer("qew",
                "qweeqqeqweqeqweqwe");
        Set<Character> expected = new LinkedHashSet<>();
        for (char c : new char[]{'e', 'q', 'w'}) {
            expected.add(c);
        }
        assertEquals(expected.toString(), sa.DefineSortingOrder(sa.ContainInAtLeastOneString(), "HASH 1").toString());
    }

    @Test
    void NullPointerExceptionTest() {
        StringAnalyzer s1 = new StringAnalyzer(null, null);
        StringAnalyzer s2 = new StringAnalyzer("null", null);
        StringAnalyzer s3 = new StringAnalyzer(null, "null");
        StringAnalyzer s4 = new StringAnalyzer("null", "null");
        Assertions.assertThrows(NullPointerException.class, s1::ContainInBothStrings);
        Assertions.assertThrows(NullPointerException.class, s1::ContainInFirstAbsentInSecond);
        Assertions.assertThrows(NullPointerException.class, s1::ContainInAtLeastOneString);
    }

    @Test
    void IllegalArgumentExceptionTest() {
        StringAnalyzer sa = new StringAnalyzer("null", "null");
        Assertions.assertThrows(IllegalArgumentException.class, () -> sa.DefineSortingOrder(sa.ContainInBothStrings(), "HASH -2"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> sa.DefineSortingOrder(sa.ContainInBothStrings(), "aWSD 3"));
    }

}
