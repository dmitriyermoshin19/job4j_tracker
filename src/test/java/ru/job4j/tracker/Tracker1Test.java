package ru.job4j.tracker;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Tracker1Test {

    @Test
    public void tracker1EnumEagerLoading() {
        Tracker1 tracker = Tracker1.ITEM;
        Tracker1 result = tracker.getItem();
        assertThat(result, is(tracker));
    }
    @Test
    public void tracker2LazyLoading() {
        ITracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }
    @Test
    public void tracker3EagerLoading() {
        Item item =  new Item("Single");
        Item single = Tracker3.getItem();
        assertThat(single, is(item));
    }
    @Test
    public void tracker4LazyLoading() {
        Item item =  new Item("Single");
        Item single = Tracker4.getItem();
        assertThat(single, is(item));
    }
}
