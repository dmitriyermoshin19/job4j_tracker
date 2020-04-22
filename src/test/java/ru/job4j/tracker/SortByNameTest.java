package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortByNameTest {
    @Test
    public void comparatorIncrease() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item("Task1");
        Item item2 = new Item("Task2");
        Item item3 = new Item("Task3");
        Item item4 = new Item("Task4");
        items.add(item4);
        items.add(item3);
        items.add(item2);
        items.add(item1);
        Collections.sort(items, new SortByNameIncrease());
        List<Item> expected = List.of(item1, item2, item3, item4);
        assertThat(expected, is(items));
    }
    @Test
    public void comparatorDecrease() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item("Task1");
        Item item2 = new Item("Task2");
        Item item3 = new Item("Task3");
        Item item4 = new Item("Task4");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        Collections.sort(items, new SortByNameDecrease());
        List<Item> expected = List.of(item4, item3, item2, item1);
        assertThat(expected, is(items));
    }
}
