package com.epam.rd.java.basic.practice2;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class ListImplTest {

    @Test
    public void shouldAddNewItemsAndReturnStringRepresentationOfList()
    {
        ListImpl list = new ListImpl();

        list.addFirst("f");
        list.addLast("s");
        list.addLast("l");

        assertThat(list.toString(), CoreMatchers.containsString("[f, s, l"));
    }

}
