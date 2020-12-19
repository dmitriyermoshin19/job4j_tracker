package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReplaceActionTest {

    @Test
    public void execute() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        tracker.findAll().get(0).setId("1");
        String replacedName = "replaced item";
        ReplaceAction rep = new ReplaceAction("=== Edit item ===");

        Input input = mock(Input.class);

        when(input.askStr("Enter id: ")).thenReturn("1");
        when(input.askStr("Edit name: ")).thenReturn(replacedName);

        rep.execute(input, tracker);

        Item replaced = tracker.findAll().get(0);
        assertThat(replaced.getName(), is(replacedName));
    }
}