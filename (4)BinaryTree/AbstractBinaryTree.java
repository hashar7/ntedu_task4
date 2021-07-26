package com.company;

import java.security.InvalidKeyException;

public abstract class AbstractBinaryTree<E extends Comparable<E>> {
    abstract BinaryTreeNode<E> search(E key);

    abstract BinaryTreeNode<E> getMinimum() throws RuntimeException;

    abstract BinaryTreeNode<E> getMaximum() throws RuntimeException;

    abstract void insert(E key);

    abstract void delete(E key) throws InvalidKeyException;

    abstract String preOrder();

    abstract String inOrder();

    abstract String postOrder();
}
