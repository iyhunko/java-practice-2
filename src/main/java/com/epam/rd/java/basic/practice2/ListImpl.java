package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private Node head = null;
    private Node tail = null;
    private int count;

    static class Node {
        Object data;
        Node next;
        Node prev;

        public Node(Object data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public ListImpl() {
        count = 0;
    }

    @Override
    public void clear() {
        count = 0;
        head = null;
        tail = null;
    }

    @Override
    public int size() {
        return count;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        public Node current = head;

        @Override
        public boolean hasNext() {
            boolean result = true;

            if (current == null || current.next == null) {
                result = false;
            }

            return result;
        }

        @Override
        public Object next() {
            return current.next;
        }

    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        Node oldHead = head;

        if (head == null) {
            head = newNode;
            if (tail != null) {
                head.next = tail;
                tail.prev = head;
            }
        } else {
            oldHead.prev = newNode;
            head = newNode;
            head.next = oldHead;
            if (tail == null) {
                tail = oldHead;
            }
        }

        count++;
    }

    @Override
    public void addLast(Object element) {
        // TODO: use trait.

        Node newNode = new Node(element);

        if (head == null) {
            head = newNode;
        } else if (tail == null) {
            tail = newNode;
            tail.prev = head;
            head.next = tail;
        } else {
            tail.next = newNode;
            newNode.prev = tail;

            tail = newNode;
        }

        count++;
    }

    @Override
    public void removeFirst() {
        head = head.next;
        head.prev = null;

        if (count > 0) {
            count--;
        }
    }

    @Override
    public void removeLast() {
        Node itemToBeRemoved = tail;

        tail = itemToBeRemoved.prev;
        tail.next = null;

        if (count > 0) {
            count--;
        }
    }

    @Override
    public Object getFirst() {
        return head.data;
    }

    @Override
    public Object getLast() {
        return tail.data;
    }

    @Override
    public Object search(Object element) {

        Node currentNode = head;

        do {
            if (currentNode.data.equals(element)) {
                return currentNode.data;
            }

            if (currentNode.next != null && currentNode.next.data.equals(element)) {
                return currentNode.next.data;
            }

            currentNode = currentNode.next;
        } while (currentNode != null);

        return null;
    }

    @Override
    public boolean remove(Object element) {
        boolean result = false;

        Node currentNode = head;

        do {
            if (currentNode.data.equals(element)) {
                if (currentNode.equals(head)) {
                    head = head.next;
                    head.prev = null;
                } else if (currentNode.equals(tail)) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    currentNode.prev.next = currentNode.next;
                    currentNode.next.prev = currentNode.prev;
                }

                result = true;
                count--;
                break;
            }

            currentNode = currentNode.next;
        } while (currentNode != null);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node currentNode = head;

        do {
            result.append(currentNode.data.toString());

            currentNode = currentNode.next;

            if (currentNode != null) {
                result.append(", ");
            }
        } while (currentNode != null);

        return "[" + result.toString() + "]";
    }

    public static void main(String[] args) {
        ListImpl newListImplementation = new ListImpl();

        newListImplementation.addFirst("Third item");
        newListImplementation.addFirst("Second item");
        newListImplementation.addFirst("First item");

        newListImplementation.addLast("Last item");

        System.out.println("List:");
        System.out.println(newListImplementation);

        System.out.println("Size:");
        System.out.println(newListImplementation.size());

        System.out.println("First:");
        System.out.println(newListImplementation.getFirst());
        System.out.println("Last:");
        System.out.println(newListImplementation.getLast());

        System.out.println("Search second:");
        System.out.println(newListImplementation.search("Second item"));

        newListImplementation.removeFirst();

        System.out.println("After remove first:");
        System.out.println(newListImplementation);

        newListImplementation.removeLast();
        System.out.println("After remove last:");
        System.out.println(newListImplementation);

        newListImplementation.addLast("Last item");
        System.out.println("After add last:");
        System.out.println(newListImplementation);

        newListImplementation.remove("Third item");
        System.out.println("After remove:");
        System.out.println(newListImplementation);
        System.out.println(newListImplementation.size());
    }
}
