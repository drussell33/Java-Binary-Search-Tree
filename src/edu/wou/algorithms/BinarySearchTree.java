package edu.wou.algorithms;

import java.security.InvalidKeyException;
import java.util.*;
import java.util.function.Consumer;

import static java.lang.Math.pow;

public class BinarySearchTree<K extends Comparable<K>, V> implements IBinarySearchTree<K, V> {
    private class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left = null;
        Node<K, V> right = null;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;

        }

        private int getChildrenCount() {
            int childcount = 0;
            if (left != null) {
                ++childcount;
            }
            if (right != null) {
                ++childcount;
            }
            return childcount;
        }

    }

    private Node<K, V> root = null;

    @Override
    public void insert(K key, V value) {
        try {
            this.root = insert(root, key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null) {
            node = new Node<>(key, value);
            return node;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key, value);
        } else {
            return node;
        }
        return node;
    }


    @Override
    public void remove(K key) throws InvalidKeyException {
        if (this.root == null) throw new InvalidKeyException();

        this.root = remove(this.root, key);
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) return node;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            var maxNode = maxNode(node.left);

            node.key = maxNode.key;
            node.value = maxNode.value;

            node.left = remove(node.left, node.key);
        }
        return node;
    }

    private Node<K, V> maxNode(Node<K, V> node) {
        var maxNode = node;
        while (maxNode.right != null) {
            maxNode = maxNode.right;
        }
        return maxNode;
    }

    @Override
    public V find(K key) throws InvalidKeyException {
        if(this.root == null) throw new InvalidKeyException();
        return find(this.root, key);
    }

    private V find(Node<K,V> node, K key) {
        if(node == null) return null;
        if(node.value == key) return node.value;

        else if(key.compareTo(node.key) < 0) {
           return find(node.left, key);
        } else{
          return  find(node.right, key);
        }
    }

    @Override
    public void preOrderTraversal(Consumer visit) {
        preOrderTraversal(this.root, visit);
    }

    private void preOrderTraversal(Node<K, V> node, Consumer visit) {
        if (node == null) {
            return;
        }

        visit.accept(node.value);

        preOrderTraversal(node.left, visit);

        preOrderTraversal(node.right, visit);

    }

    @Override
    public void inOrderTraversal(Consumer visit) {
        inOrderTraversal(this.root, visit);
    }

    private void inOrderTraversal(Node<K, V> node, Consumer visit) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left, visit);

        visit.accept(node.value);

        inOrderTraversal(node.right, visit);

    }


    @Override
    public void postOrderTraversal(Consumer visit) {
        postOrderTraversal(this.root, visit);
    }

    private void postOrderTraversal(Node<K, V> node, Consumer visit) {
        if (node == null) {
            return;
        }

        postOrderTraversal(node.left, visit);

        postOrderTraversal(node.right, visit);

        visit.accept(node.value);
    }

    @Override
    public void breadthFirstTraversal(Consumer visit) {
        if (this.root == null) return;

        var toVisitQueue = new LinkedList<Node<K, V>>();

        toVisitQueue.add(this.root);

        while (!toVisitQueue.isEmpty()) {
            var node = toVisitQueue.poll();

            visit.accept(node.value);

            if (node.left != null) toVisitQueue.add(node.left);
            if (node.right != null) toVisitQueue.add(node.right);
        }
    }

    @Override
    public int size() {
        return size(root) + 1;
    }

    private int size(Node<K, V> node) {
        if (node == null) {
            return -1;
        }

        var countLeft = 0;
        var countRight = 0;

        if (node.left != null) {
            ++countLeft;
            countLeft += size(node.left);
        }

        if (node.right != null) {
            ++countRight;
            countRight += size(node.right);
        }

        return countLeft + countRight;
    }

    @Override
    public int getHeight() {
        return getHeight(this.root);
    }

    private int getHeight(Node<K, V> node) {
        if (node == null) return 0;
        else {
            var leftDepth = getHeight(node.left);
            var rightDepth = getHeight(node.right);
            if (leftDepth >= rightDepth) {
                return leftDepth + 1;
            } else {
                return rightDepth + 1;
            }
        }
    }

    @Override
    public boolean isFull() {
        return isFull(this.root);
    }

    public boolean isFull(Node<K, V> node) {
        boolean finalAnswerLeft = true;
        boolean finalAnswerRight = true;

        if (node == null) return false;
        else {
            if (node.getChildrenCount() == 2) {
                finalAnswerLeft = isFull(node.left);
                finalAnswerRight = isFull(node.right);
            } else if (node.getChildrenCount() == 1) {
                finalAnswerLeft = isFull(node.left);
                finalAnswerRight = isFull(node.right);
            } else if (node.getChildrenCount() == 0) {
                return true;
            } else {
                return false;
            }
            return finalAnswerLeft == finalAnswerRight == true;
        }
    }


    @Override
    public boolean isPerfect() {
        return isPerfect(this.root);
    }

    private boolean isPerfect(Node<K, V> node) {

        if(this.size() != (pow(2, this.getHeight())) - 1){
            return false;

        }else {
        boolean finalAnswerLeft = true;
        boolean finalAnswerRight = true;

        if (node == null) return false;
        else {
            if (node.getChildrenCount() == 2) {
                finalAnswerLeft = isFull(node.left);
                finalAnswerRight = isFull(node.right);
            } else if (node.getChildrenCount() == 1) {
                return false;
            } else if (node.getChildrenCount() == 0) {
                return true;
            } else {
                return false;
            }
            return finalAnswerLeft == finalAnswerRight == true;
        }
    }
    }

    @Override
    public boolean isComplete() {
        return isComplete(this.root);
    }

    private boolean isComplete(Node<K, V> node) {
        if(this.size() != (pow(2, this.getHeight())) - 2){
            return false;
        } else {
            if (node == null) return false;

            Queue<Node<K, V>> toVisitQueue = new LinkedList<>();

            toVisitQueue.add(node);

            var count = 0;
            while (!toVisitQueue.isEmpty()) {
                var currentNode = toVisitQueue.remove();
                if (currentNode != null) {
                    ++count;
                    toVisitQueue.add(currentNode.left);
                    toVisitQueue.add(currentNode.right);
                } else {
                    break;
                }
            }

            if (this.size() == count) {
                return true;
            } else {
                return false;
            }
        }
    }
}
