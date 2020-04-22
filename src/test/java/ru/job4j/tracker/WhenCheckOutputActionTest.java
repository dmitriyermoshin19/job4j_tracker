package ru.job4j.tracker;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WhenCheckOutputActionTest {
    @Test
    public void findAllAction() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        ITracker tracker = new Tracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        FindAllAction act = new FindAllAction("All items:");
        act.execute(new StubInput(new String[]  {}), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(item.getId() + " " + item.getName())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
    @Test
    public void findItemsByNameAction() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        ITracker tracker = new Tracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        Input input = new StubInput(new String[]  {item.getName()});
        FindItemsByNameAction act = new FindItemsByNameAction("=== Find items by name ===");
        act.execute(input, tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(item.getId() + " " + item.getName())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}
