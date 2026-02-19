import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            root.insert(value);
        }
    }

    List<T> getAsSortedList() {
        var result = new ArrayList<T>();
        if (root != null) {
            root.inOrderTraversal(result);
        }
        return result;
    }

    List<T> getAsLevelOrderList() {
        return traverseLevels(Stream.ofNullable(root).toList()).map(Node::getData).toList();
    }

    Stream<Node<T>> traverseLevels(List<Node<T>> previous) {
        if (previous.isEmpty()) {
            return Stream.empty();
        }
        var current = previous.stream()
                .flatMap(node -> Stream.of(node.getLeft(), node.getRight()))
                .filter(Objects::nonNull)
                .toList();
        return Stream.concat(previous.stream(), traverseLevels(current));
    }

    Node<T> getRoot() {
        return root;
    }

    static class Node<T extends Comparable<T>> {
        private Node<T> left;
        private Node<T> right;
        private final T data;

        Node(T data) {
            this.data = data;
        }

        void inOrderTraversal(List<T> result) {
            if (getLeft() != null) {
                getLeft().inOrderTraversal(result);
            }
            result.add(getData());
            if (getRight() != null) {
                getRight().inOrderTraversal(result);
            }
        }

        void insert(T value) {
            if (value.compareTo(getData()) <= 0) {
                if (getLeft() == null) {
                    left = new Node<>(value);
                } else {
                    getLeft().insert(value);
                }
            } else {
                if (getRight() == null) {
                    right = new Node<>(value);
                } else {
                    getRight().insert(value);
                }
            }
        }

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }

        T getData() {
            return data;
        }

    }
}
