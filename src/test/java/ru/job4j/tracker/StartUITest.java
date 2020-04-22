package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenAddItem() {
        String[] answers = {"Fix PC"};
        Input input = new StubInput(answers);
        ITracker tracker = new Tracker();
        new CreateAction("=== Create a new Item ====").execute(input, tracker);
        Item created = tracker.findAll().get(0);
        Item expected = new Item("Fix PC");
        assertThat(created.getName(), is(expected.getName()));
    }
    @Test
    public void whenReplaceItem() {
        ITracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {item.getId(), "replaced item"};
        new ReplaceAction("=== Edit item ===").execute(new StubInput(answers), tracker);
        Item replaced = tracker.findById(item.getId());
        assertThat(replaced.getName(), is("replaced item"));
    }
    @Test
    public void whenDeleteItem() {
        ITracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {item.getId()};
        new DeleteAction("=== Delete item ===").execute(new StubInput(answers), tracker);
        Item replaced = tracker.findById(item.getId());
        Item rst = null;
        assertThat(replaced, is(rst));
    }
}
