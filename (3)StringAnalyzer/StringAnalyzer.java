package com.company;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringAnalyzer {
    private final String s1;
    private final String s2;

    public StringAnalyzer() {
        s1 = null;
        s2 = null;
    }
    public StringAnalyzer(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }
    public String getS1() {
        return s1;
    }
    public String getS2() {
        return s2;
    }
    public LinkedHashSet<Character> ContainInBothStrings() throws NullPointerException {
        if (s2 == null || s1 == null) {
            throw new NullPointerException();
        }
        int length = s1.length();
        return IntStream.range(0, length).filter(i ->
                s2.indexOf(s1.charAt(i)) != -1).mapToObj(s1::charAt).collect(Collectors.toCollection(LinkedHashSet::new));
    }
    public LinkedHashSet<Character> ContainInFirstAbsentInSecond() throws NullPointerException {
        if (s2 == null || s1 == null) {
            throw new NullPointerException();
        }
        int length = s1.length();
        return IntStream.range(0, length).filter(i ->
                s2.indexOf(s1.charAt(i)) == -1).mapToObj(s1::charAt).collect(Collectors.toCollection(LinkedHashSet::new));
    }
    public LinkedHashSet<Character> ContainInAtLeastOneString() throws NullPointerException {
        if (s2 == null || s1 == null) {
            throw new NullPointerException();
        }
        var result = IntStream.range(0, s1.length()).mapToObj(s1::charAt).collect(Collectors.toCollection(LinkedHashSet::new));
        IntStream.range(0, s2.length()).mapToObj(s2::charAt).forEach(result::add);
        return result;
    }
    public Set<Character> DefineSortingOrder(Set<Character> set, String order) throws IllegalArgumentException {
        if (order == null) {
            throw new IllegalArgumentException();
        }
        switch (order) {
            case "USUAL":
                return new TreeSet<>(set);
            case "REVERSE":
                return new TreeSet<>(set).descendingSet();
            default:
                if (!order.startsWith("HASH")) {
                    throw new IllegalArgumentException();
                }
                String[] split = order.split(" ");
                int n = Integer.parseInt(split[1]);
                if (n < 0) {
                    throw new IllegalArgumentException();
                }
                TreeSet<Character> resultSet = new TreeSet<>(new Comparator<Character>() {
                    @Override
                    public int compare(Character o1, Character o2) {
                        return LeftShift(o1.hashCode(), n) - LeftShift(o2.hashCode(), n);
                    }
                });
                resultSet.addAll(set);
                return resultSet;
        }
    }
    private int LeftShift(int num, int offset) {
        return (num << (offset % 32)) | (num >> (32 - (offset % 32)));
    }
}

