package com.epam.rd.java.basic.practice2;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class ArrayImplTest {

    @Test
    public void shouldSetNewItemsAndReturnStringRepresentationOfArray()
    {
        ArrayImpl arr = new ArrayImpl(3);

        arr.set(0, 1);
        arr.set(1, 2);
        arr.set(2, 3);

        assertThat(arr.toString(), CoreMatchers.containsString("[1, 2, 3]"));
    }
}
