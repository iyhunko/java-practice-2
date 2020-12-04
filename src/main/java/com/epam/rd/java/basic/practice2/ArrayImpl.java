package com.epam.rd.java.basic.practice2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private Object[] items;

    private final int arrayLength;

    public ArrayImpl(int size) {
        arrayLength = size;
        items = new Object[size];
    }

	@Override
    public void clear() {
        items = new Object[arrayLength];
    }

	@Override
    public int size() {
        int count = 0;

        for (Object item : items)
            if (item != null)
                count++;

	    return count;
    }
	
	@Override
    public Iterator<Object> iterator() {
	    return new IteratorImpl();
    }
	
	private class IteratorImpl implements Iterator<Object> {

        private int nextIndex = 0;

        @Override
        public boolean hasNext() {
            boolean result = true;
            if (items[nextIndex] == null) {
                result = false;
            }

            return result;
        }

        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();

            Object nextItem = items[nextIndex];

            nextIndex++;

            return nextItem;
        }

    }
	
	@Override
    public void add(Object element) {
        items[size()] = element;
    }

	@Override
    public void set(int index, Object element) {
        items[index] = element;
    }

	@Override
    public Object get(int index) {
	    return items[index];
    }

	@Override
    public int indexOf(Object element) {
        for (int i = 0; i < items.length; i++)
            if (element.equals(items[i]))
                return i;

        throw new NoSuchElementException();

    }

	@Override
    public void remove(int index) {
        items[index] = null;
    }

    @Override
    public String toString() {
	    return Arrays.toString(items);
    }

    public static void main(String[] args) {
        ArrayImpl arrayImpl = new ArrayImpl(3);
        arrayImpl.add(3);
        arrayImpl.add(5);
        arrayImpl.add(2);

        System.out.println(arrayImpl.get(0));
        System.out.println(arrayImpl.get(1));
        System.out.println(arrayImpl.get(2));
        System.out.println(arrayImpl.size());
        System.out.println(arrayImpl.indexOf(2));
    }

}
