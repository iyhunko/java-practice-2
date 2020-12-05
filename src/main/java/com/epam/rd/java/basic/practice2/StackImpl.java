package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class StackImpl implements Stack {

    public Node head = null;
    public Node tail = null;
    public int count;

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

    @Override
    public void clear() {
        // TODO: use trait. Same as in queue and ListImpl
        head = null;
        tail = null;
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private static class IteratorImpl implements Iterator<Object> {

        public QueueImpl.Node current;

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
    public void push(Object element) {
        // TODO: use trait. This method is same as ListImpl.addLast()
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
    public Object pop() {
        Node elementToBeReturned = tail;

        tail = elementToBeReturned.prev;
        tail.next = null;

        if (count > 0) {
            count--;
        }

        return elementToBeReturned.data;
    }

    @Override
    public Object top() {
        return tail.data;
    }

    @Override
    public String toString() {
        // TODO: use trait. Method is same as in ListImpl
        StringBuilder result = new StringBuilder();
        Node currentNode = head;

        while (currentNode != null) {
            result.append(currentNode.data.toString());

            currentNode = currentNode.next;

            if (currentNode != null) {
                result.append(", ");
            }
        }

        return "[" + result.toString() + "]";
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();

        stack.push("first");
        stack.push("second");
        stack.push("third");
        System.out.println(stack);

        System.out.println("Size: ");
        System.out.println(stack.size());

        System.out.println("Top: ");
        System.out.println(stack.top());

        System.out.println("Pop: ");
        System.out.println(stack.pop());
        System.out.println("After pop: ");
        System.out.println(stack);

        System.out.println("Size: ");
        System.out.println(stack.size());
    }

}
