package com.epam.rd.java.basic.practice2;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class StackImplTest {
    @Test
    public void shouldAddNewItemsAndReturnStringRepresentationOfStack()
    {
        StackImpl stack = new StackImpl();

        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertThat(stack.toString(), CoreMatchers.containsString("[1, 2, 3]"));
    }
}
