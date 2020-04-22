package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        ITracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        ITracker tracker = new Tracker();
        Item prev = new Item("test");
        tracker.add(prev);
        Item previous = new Item("test1");
        tracker.add(previous);
        Item next = new Item("test2");
        //next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
    @Test
    public void findByName() {
        ITracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        List<Item> result = tracker.findByName(item.getName());
        assertThat(result, is(List.of(item)));
    }
    @Test
    public void findAll() {
        ITracker tracker = new Tracker();
        Item aaa = tracker.add(new Item("aaa"));
        Item bbb = tracker.add(new Item("bbb"));
        Item ccc = tracker.add(new Item("ccc"));
        assertThat(tracker.findAll(), is(List.of(aaa, bbb, ccc)));
    }
    @Test
    public void deleteItem() {
        ITracker tracker = new Tracker();
        Item aaa = tracker.add(new Item("aaa"));
        Item bbb = tracker.add(new Item("bbb"));
        Item ccc = tracker.add(new Item("ccc"));
        tracker.delete(bbb.getId());
        assertThat(tracker.findAll(), is(List.of(aaa, ccc)));
    }

}
