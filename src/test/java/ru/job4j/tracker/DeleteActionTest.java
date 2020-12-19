package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest {

    @Test
    public void execute() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("new item1");
        Item item2 = new Item("new item2");
        tracker.add(item1);
        tracker.add(item2);
        tracker.findAll().get(0).setId("1");
        DeleteAction del = new DeleteAction("=== Delete item ===");

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("1");

        del.execute(input, tracker);
        Item replaced = tracker.findAll().get(0);
        assertThat(replaced.getName(), is("new item2"));
    }
}