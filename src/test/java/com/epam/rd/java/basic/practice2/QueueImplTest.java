package com.epam.rd.java.basic.practice2;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class QueueImplTest {

    @Test
    public void shouldAddNewItemsAndReturnStringRepresentationOfQueue()
    {
        QueueImpl queue = new QueueImpl();

        queue.enqueue("4");

        assertThat(queue.toString(), CoreMatchers.containsString("[4"));
    }

}
