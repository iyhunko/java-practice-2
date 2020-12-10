package com.epam.rd.java.basic.practice2;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class QueueImplTest {

    @Test
    public void shouldAddNewItemsAndReturnStringRepresentationOfQueue()
    {
        QueueImpl queue = new QueueImpl(new String[]{"1", "2", "3"});

        queue.enqueue("4");

        assertThat(queue.toString(), CoreMatchers.containsString("[1, 2, 3, 4"));
    }

}
