package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class QueueImpl implements Queue {

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

    public QueueImpl(Object[] items) {
        for (int i = 0; i < items.length; i++) {
            if (i == 0) {
                head = new Node(items[i]);
            } else if (i == 1) {
                tail = new Node(items[i]);
                tail.prev = head;
                head.next = tail;
            } else {
                // TODO: use trait .addToEnd(). same as in ListImpl
                Node newNode = new Node(items[i]);
                tail.next = newNode;
                newNode.prev = tail;

                tail = newNode;
            }

            count++;
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        count = 0;
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
    public void enqueue(Object element) {
        // TODO: use trait becuase this method is same as addLast of ListImpl
        Node newNode = new Node(element);

        if (head == null) {
            head = newNode;
        } else if (tail == null) {
            tail = newNode;
            tail.prev = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;

            tail = newNode;
        }

        count++;
    }

    @Override
    public Object dequeue() {
        try {
            // TODO: use trait to reuse ListImpl.removeFirst()
            head = head.next;
            head.prev = null;

            if (count > 0) {
                count--;
            }
        } catch (Exception $exception) {
            // TODO: Log error message
            return false;
        }

        return true;
    }

    @Override
    public Object top() {
        return head.data;
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

        QueueImpl queue = new QueueImpl(
                new String[]{"first", "second", "third", "forth"}
        );
        System.out.println(queue);

        queue.enqueue("last");
        System.out.println("After enqueue:");
        System.out.println(queue);

        queue.dequeue();
        System.out.println("After dequeue:");
        System.out.println(queue);

        System.out.println("Top method:");
        System.out.println(queue.top());

        queue.clear();
        System.out.println("After clear:");
        System.out.println(queue);
    }

}
