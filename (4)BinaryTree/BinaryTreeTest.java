package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeTest {
    private BinaryTree<Integer> bt;

    public BinaryTreeTest() {
        bt = new BinaryTree<Integer>();
        IntStream.of(21, 10, 40, 42, 31, 22, 35).forEachOrdered(i -> bt.insert(i));
    }

    @Test
    void SearchTest() {
        assertEquals("31", bt.search(31).getKey().toString());
    }

    @Test
    void MinimumTest() {
        assertEquals(10, bt.getMinimum().getKey());
    }

    @Test
    void MaximumTest() {
        assertEquals(42, bt.getMaximum().getKey());
    }

    @Test
    void PreOrderTest() {
        assertEquals("21 10 40 31 22 35 42 ", bt.preOrder());
    }

    @Test
    void InOrderTest() {
        assertEquals("10 21 22 31 35 40 42 ", bt.inOrder());
    }

    @Test
    void PostOrderTest() {
        assertEquals("10 22 35 31 42 40 21 ", bt.postOrder());
    }

    @Test
    void ExceptionsTest() {
        Assertions.assertThrows(InvalidKeyException.class, () -> bt.delete(100));
        bt = null;
        Assertions.assertThrows(RuntimeException.class, () -> bt.getMinimum());
        Assertions.assertThrows(RuntimeException.class, () -> bt.getMaximum());
    }
}
