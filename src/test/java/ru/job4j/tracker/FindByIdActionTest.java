package ru.job4j.tracker;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionTest {

    @Test
    public void execute() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("new item1");
        Item item2 = new Item("new item2");
        tracker.add(item1);
        tracker.add(item2);
        tracker.findAll().get(1).setId("1");
        FindByIdAction find = new FindByIdAction("=== FindById item ===");

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("1");

        find.execute(input, tracker);
        Item item = tracker.findAll().get(1);
        assertThat(item.getName(), is("new item2"));
    }
}