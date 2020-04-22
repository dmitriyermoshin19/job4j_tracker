package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {

    private TrackerSQL trackerSQL;
    private List<Item> result;

    @Before
    public void setUp() {
        trackerSQL = new TrackerSQL();
        trackerSQL.init();
    }

    @Test
    public void checkConnection() {
        assertThat(trackerSQL.init(), is(true));
    }

    @Test
    public void whenAddItem() {
        Item item = new Item("testNameFirst", "testDescriptionFirst");
        trackerSQL.add(item);
        assertThat(trackerSQL.findById(item.getId()).getName(), is("testNameFirst"));
    }

    @Test
    public void whenReplaceNameOfItem() {
        Item item = new Item("testNameSecond", "testDescriptionSecond");
        Item item1 = new Item("testNameThird", "testDescriptionThird");
        trackerSQL.add(item);
        trackerSQL.replace(item.getId(), item1);
        assertThat(trackerSQL.findById(item.getId()).getName(), is("testNameThird"));
    }

    @Test
    public void whenFindAllItem() {
        Item item = new Item("testNameFourth", "testDescriptionFourth");
        Item item1 = new Item("testNameFifth", "testDescriptionFifth");
        trackerSQL.add(item);
        trackerSQL.add(item1);
        result = trackerSQL.findAll();
        int id = 4;
        assertThat(result.get(id - 1).getDescription(), is("testDescriptionFifth"));
    }

    @Test
    public void whenFindItemsByName() {
        result = trackerSQL.findByName("testNameFifth");
        assertThat(result.get(0).getDescription(), is("testDescriptionFifth"));
    }

    @Test
    public void whenFindItemById() {
        Item item = new Item("testNameSixth", "testDescriptionSixth");
        trackerSQL.add(item);
        assertThat(trackerSQL.findById(item.getId()).getDescription(), is("testDescriptionSixth"));
    }
}
