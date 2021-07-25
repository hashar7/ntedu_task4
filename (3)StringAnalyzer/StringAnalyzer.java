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

    /**
     * Конструктор по умолчанию без параметров.
     */
    public StringAnalyzer() {
        s1 = null;
        s2 = null;
    }

    /**
     * Конструктор от двух строковых праметров.
     * @param s1 строка номер 1.
     * @param s2 строка номер 2.
     */
    public StringAnalyzer(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    /**
     * Метод возвращает {@link LinkedHashSet}, содержащий все символы, входящие в обе строки.
     *
     * @return {@link LinkedHashSet}, содержащий все символы, входящие в обе строки.
     * @throws NullPointerException в случае когда хотя бы одна строка равна {@code null}.
     */
    public LinkedHashSet<Character> ContainInBothStrings() throws NullPointerException {
        if (s2 == null || s1 == null) {
            throw new NullPointerException();
        }
        int length = s1.length();
        return IntStream.range(0, length).filter(i ->
                s2.indexOf(s1.charAt(i)) != -1).mapToObj(s1::charAt).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * Метод возвращает {@link LinkedHashSet}, содержащий все символы, входящие в первую строку,
     * но отсутствующие во второй строке.
     *
     * @return {@link LinkedHashSet}, содержащий все символы, входящие в первую строку,
     * но отсутствующие во второй строке.
     * @throws NullPointerException в случае когда хотя бы одна строка равна {@code null}.
     */
    public LinkedHashSet<Character> ContainInFirstAbsentInSecond() throws NullPointerException {
        if (s2 == null || s1 == null) {
            throw new NullPointerException();
        }
        int length = s1.length();
        return IntStream.range(0, length).filter(i ->
                s2.indexOf(s1.charAt(i)) == -1).mapToObj(s1::charAt).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * Метод возвращает {@link LinkedHashSet}, содержащий все символы, входящие хотя бы в одну строку.
     *
     * @return {@link LinkedHashSet}, содержащий все символы, входящие хотя бы в одну строку.
     * @throws NullPointerException в случае когда хотя бы одна строка равна {@code null}.
     */
    public LinkedHashSet<Character> ContainInAtLeastOneString() throws NullPointerException {
        if (s2 == null || s1 == null) {
            throw new NullPointerException();
        }
        var result = IntStream.range(0, s1.length()).mapToObj(s1::charAt).collect(Collectors.toCollection(LinkedHashSet::new));
        IntStream.range(0, s2.length()).mapToObj(s2::charAt).forEach(result::add);
        return result;
    }

    /**
     * Метод сортирует свходно набор {@link Set} в соотвествии со стрококвым параметром {@code order}.
     *
     * @param set   набор, который необходимо отсортировать.
     * @param order порядок сортировки: {@code USUAL} — для обычной сортировки по возрастанию;
     *              {@code REVERSE} — для сортировки по убыванию;
     *              {@code HASH N} — для сортировки в проядке возрастания циклического сдвига влево
     *              на {@code N} разрядов хэш-функции символа. Число {@code N} должно быть неотрицательным целым числом.
     * @return отсортированный набор, в соответствии с указанным порядком.
     * @throws IllegalArgumentException в случае неверного указания порядка сортировки или {@code null} вместо {@param set}.
     */
    public Set<Character> DefineSortingOrder(Set<Character> set, String order) throws IllegalArgumentException {
        if (order == null || set == null) {
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

    /**
     * Метод реализует циклический сдвиг влево целого числа {@link int}.
     *
     * @param num    число, которое необходимо сдвинуть.
     * @param offset количество разрядов, на которые надо сдвинуть число.
     * @return число, полученное циклическим сдвигом {@param num} на {@param offset} разрядов влево.
     */
    private int LeftShift(int num, int offset) {
        return (num << (offset % 32)) | (num >> (32 - (offset % 32)));
    }
}

