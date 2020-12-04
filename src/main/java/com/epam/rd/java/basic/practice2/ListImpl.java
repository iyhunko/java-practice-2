package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    public Node head = null;
    public Node tail = null;
    public int count;

    static class Node {
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
            this.next = null;
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
        return new IteratorImpl(head);
    }

    private static class IteratorImpl implements Iterator<Object> {

        public Node current;

        public IteratorImpl(Node node) {
            current = node;
        }

        @Override
        public boolean hasNext() {
            return current != null && current.next != null;
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
            tail = newNode;
        } else {
            if (oldHead.equals(tail)) {
                head = newNode;
                head.next = tail;
            } else {
                head = newNode;
                head.next = oldHead;
            }
        }
        count++;
    }

    @Override
    public void addLast(Object element) {
        Node newNode = new Node(element);
        Node oldLast = tail;

        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            if (head.equals(tail)) {
                tail = newNode;
                head.next = tail;
            } else {
                oldLast.next = newNode;
                tail = newNode;
            }
        }

        count++;
    }

    @Override
    public void removeFirst() {
        head = head.next;

        if (count > 0) {
            count--;
        }
    }

    @Override
    public void removeLast() {
        IteratorImpl iterator = (IteratorImpl) iterator();

        while (iterator.hasNext()) {
            Node item = iterator.current;
            Node nextAfterCurrent = item.next;
            if (nextAfterCurrent.equals(tail)) {
                nextAfterCurrent.next = null;
                tail = nextAfterCurrent;
            }
            iterator.next();
        }

        if (count > 0) {
            count--;
        }
    }

    @Override
    public Object getFirst() {
        return head;
    }

    @Override
    public Object getLast() {
        return tail;
    }

    @Override
    public Object search(Object element) {
        IteratorImpl iterator = (IteratorImpl) iterator();

        do {
            Node item = iterator.current;

            if (item.next.data.equals(element)) {
                return item.next;
            }

            if (item.data.equals(element)) {
                return item;
            }

            iterator.next();
        } while (iterator.hasNext());

        throw new NoSuchElementException();
    }

    @Override
    public boolean remove(Object element) {
        boolean result = false;
        IteratorImpl iterator = (IteratorImpl) iterator();

        Node beforeLast = tail;
        do {


            Node item = iterator.current;

            if (item.data.equals(element)) {
                beforeLast.next = item.next;
                item.next = null;

                result = true;
                break;
            }

            if (iterator.hasNext()) {
                beforeLast = iterator.current;
            }

            iterator.next();
        } while (iterator.hasNext());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        IteratorImpl iterator = (IteratorImpl) iterator();

        do {
            result.append(iterator.current.toString());

            iterator.next();
        } while (iterator.hasNext());

        String resultString = result.toString();
        if (!resultString.equals("")) {
            resultString = "[" + resultString + "]";
        }

        return resultString;
    }

    public static void main(String[] args) {

    }
}
