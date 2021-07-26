package com.company;

public class BinaryTreeNode<E extends Comparable<E>> {
    private E key;
    private BinaryTreeNode<E> leftChild;
    private BinaryTreeNode<E> rightChild;
    private BinaryTreeNode<E> parent;

    public BinaryTreeNode() {
        key = null;
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    public BinaryTreeNode(E key) {
        this.key = key;
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    public BinaryTreeNode(E key, BinaryTreeNode<E> parent, BinaryTreeNode<E> leftChild, BinaryTreeNode<E> rightChild) {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
    }

    /**
     * Return the key stored in the node
     *
     * @return key stored in the node
     */
    public E getKey() {
        return key;
    }

    /**
     * Return the left child of the node
     *
     * @return left child of the node
     */
    public BinaryTreeNode<E> getLeftChild() {
        return leftChild;
    }

    /**
     * Return the right child of the node
     *
     * @return right child of the node
     */
    public BinaryTreeNode<E> getRightChild() {
        return rightChild;
    }

    /**
     * Return the parent of the node
     *
     * @return parent of the node
     */
    public BinaryTreeNode<E> getParent() {
        return parent;
    }

    /**
     * Sets the key stored in the node into a new key
     *
     * @param key new key that is to be stored in the node
     */
    public void setKey(E key) {
        this.key = key;
    }

    /**
     * Sets the left child of the node into a new left child
     *
     * @param leftChild new left child of the node
     */
    public void setLeftChild(BinaryTreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Sets the right child of the node into a new right child
     *
     * @param rightChild new right child of the node
     */
    public void setRightChild(BinaryTreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Sets the parent of the node into a new parent
     *
     * @param parent new parent of the node
     */
    public void setParent(BinaryTreeNode<E> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return key.toString();
    }
}
