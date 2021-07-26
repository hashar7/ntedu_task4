package com.company;

import java.security.InvalidKeyException;

public class BinaryTree<E extends Comparable<E>> extends AbstractBinaryTree<E> {
    private BinaryTreeNode<E> root;

    /**
     * Constructs an empty binary tree.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Constructs a binary tree with a given root.
     *
     * @param root — root of the binary tree.
     */
    public BinaryTree(BinaryTreeNode<E> root) {
        this.root = root;
    }

    /**
     * Search for the node that contains {@code key}.
     *
     * @param key to be searched in the binary tree.
     * @return node containing {@code key}. If the binary tree does not contain
     * {@code key}, {@code null} is returned.
     */
    @Override
    public BinaryTreeNode<E> search(E key) {
        BinaryTreeNode<E> currentNode = root;
        while ((currentNode != null) && (currentNode.getKey().compareTo(key) != 0)) {
            currentNode = key.compareTo(currentNode.getKey()) < 0 ? currentNode.getLeftChild() : currentNode.getRightChild();
        }
        return currentNode;
    }

    /**
     * Return the node that contains the minimum key in the binary tree.
     *
     * @return node that contains the minimum key.
     */
    @Override
    public BinaryTreeNode<E> getMinimum() throws RuntimeException {
        BinaryTreeNode<E> min = root;
        if (min == null) {
            throw new RuntimeException("Error! Root is null!");
        }
        while (min.getLeftChild() != null) {
            min = min.getLeftChild();
        }
        return min;
    }

    /**
     * Return the node that contains the maximum key in the binary tree.
     *
     * @return node that contains the maximum key.
     */
    @Override
    public BinaryTreeNode<E> getMaximum() throws RuntimeException {
        BinaryTreeNode<E> max = root;
        if (max == null) {
            throw new RuntimeException("Error! Root is null");
        }
        while (max.getRightChild() != null) {
            max = max.getRightChild();
        }
        return max;
    }

    /**
     * Inserts a key into the binary tree.
     *
     * @param key — the new key to be inserted into the binary tree.
     */
    @Override
    public void insert(E key) {
        BinaryTreeNode<E> childNode = null;
        BinaryTreeNode<E> parentNode = root;
        BinaryTreeNode<E> toInsert = new BinaryTreeNode<>(key);
        while (parentNode != null) {
            childNode = parentNode;
            parentNode = key.compareTo(parentNode.getKey()) < 0 ? parentNode.getLeftChild() : parentNode.getRightChild();
        }
        toInsert.setParent(childNode);
        if (childNode == null) {
            root = toInsert;
        } else if (key.compareTo(childNode.getKey()) < 0) {
            childNode.setLeftChild(toInsert);
        } else {
            childNode.setRightChild(toInsert);
        }
    }

    private void deleteHelper(BinaryTreeNode<E> u, BinaryTreeNode<E> v) {
        if (u.getParent() == null) {
            root = v;
        } else {
            if (u == u.getParent().getLeftChild()) {
                u.getParent().setLeftChild(v);
            } else {
                u.getParent().setRightChild(v);
            }
        }
        if (v != null) {
            v.setParent(u.getParent());
        }
    }

    /**
     * Deletes {@code key} from the binary tree.
     *
     * @param key to be deleted from the binary tree.
     */
    @Override
    public void delete(E key) throws InvalidKeyException {
        BinaryTreeNode<E> toDelete = search(key);
        if (toDelete == null) {
            throw new InvalidKeyException("Key not found in the binary tree.");
        }
        if (toDelete.getLeftChild() == null) {
            deleteHelper(toDelete, toDelete.getRightChild());
        } else if (toDelete.getRightChild() == null) {
            deleteHelper(toDelete, toDelete.getLeftChild());
        } else {
            BinaryTree<E> rightSubtree = new BinaryTree<>(toDelete.getRightChild());
            BinaryTreeNode<E> tmp = rightSubtree.getMinimum();
            if (tmp.getParent() != toDelete) {
                deleteHelper(tmp, tmp.getRightChild());
                tmp.setRightChild(toDelete.getRightChild());
                tmp.getRightChild().setParent(tmp);
            }
            deleteHelper(toDelete, tmp);
            tmp.setLeftChild(toDelete.getLeftChild());
            tmp.getLeftChild().setParent(tmp);
        }
    }

    private void preorderSupport(BinaryTreeNode<E> root, StringBuilder preorder) {
        if (root != null) {
            preorder.append(root).append(" ");
            preorderSupport(root.getLeftChild(), preorder);
            preorderSupport(root.getRightChild(), preorder);
        }
    }

    /**
     * Returns a preorder representation of the binary tree.
     *
     * @return preorder string of the binary tree.
     */
    @Override
    public String preOrder() {
        StringBuilder preorderTraversal = new StringBuilder();
        preorderSupport(root, preorderTraversal);
        return preorderTraversal.toString();
    }

    private void inorderSupport(BinaryTreeNode<E> root, StringBuilder inorder) {
        if (root != null) {
            inorderSupport(root.getLeftChild(), inorder);
            inorder.append(root).append(" ");
            inorderSupport(root.getRightChild(), inorder);
        }
    }

    /**
     * Returns an inorder representation of the binary tree.
     *
     * @return inorder string of the binary tree.
     */
    @Override
    public String inOrder() {
        StringBuilder inorderTraversal = new StringBuilder();
        inorderSupport(root, inorderTraversal);
        return inorderTraversal.toString();
    }

    private void postorderSupport(BinaryTreeNode<E> root, StringBuilder postorder) {
        if (root != null) {
            postorderSupport(root.getLeftChild(), postorder);
            postorderSupport(root.getRightChild(), postorder);
            postorder.append(root).append(" ");
        }
    }

    /**
     * Returns a postorder representation of the binary tree.
     *
     * @return postorder string of the binary tree.
     */
    @Override
    public String postOrder() {
        StringBuilder postorderTraversal = new StringBuilder();
        postorderSupport(root, postorderTraversal);
        return postorderTraversal.toString();
    }
}