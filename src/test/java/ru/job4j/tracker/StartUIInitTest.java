package ru.job4j.tracker;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUIInitTest {
    @Test
    public void whenExit() {
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction("Stub action");
        new StartUI().init(input, new Tracker(), List.of(action), System.out::println);
        assertThat(action.isCall(), is(true));
    }
}
